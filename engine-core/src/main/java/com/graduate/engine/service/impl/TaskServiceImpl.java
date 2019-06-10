package com.graduate.engine.service.impl;

import com.graduate.engine.enums.PriorityEnum;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.*;
import com.graduate.engine.model.*;
import com.graduate.engine.model.viewobject.TaskChargerDto;
import com.graduate.engine.model.viewobject.TaskCheckPointVo;
import com.graduate.engine.model.viewobject.TaskExecVo;
import com.graduate.engine.model.viewobject.TaskVo;
import com.graduate.engine.request.TaskCheckPointRequest;
import com.graduate.engine.request.TaskExecRequest;
import com.graduate.engine.request.TaskQuery;
import com.graduate.engine.request.TaskRequest;
import com.graduate.engine.response.PagedResult;
import com.graduate.engine.service.TaskService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务相关服务
 *
 * @author jimmy
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskChargerMapper taskChargerMapper;
    @Resource
    private PersonMemberMapper personMemberMapper;
    @Resource
    private TaskCheckPointMapper taskCheckPointMapper;
    @Resource
    private TaskExecMapper taskExecMapper;
    @Resource
    private ActivityStatusMapper activityStatusMapper;
    @Resource
    private ActivityMapper activityMapper;

    static TaskVo convertTaskToVo(Task e, ActivityStatusMapper activityStatusMapper) {
        TaskVo taskVo = BeanUtils.copyBean(e, TaskVo.class);
        taskVo.setTaskDateFrom(DateUtils.getDateStrByTimestamp(e.getTaskDateFrom()));
        taskVo.setTaskDateTo(DateUtils.getDateStrByTimestamp(e.getTaskDateTo()));
        taskVo.setTaskStatusName(activityStatusMapper.selectByPrimaryKey(e.getTaskStatusId()).getActStatusName());
        return taskVo;
    }

    @Override
    public Long addTask(TaskRequest request) {
        Task task = BeanUtils.copyBean(request, Task.class);
        task.setTaskStatusId(0L);
        try {
            task.setTaskDateTo(DateUtils.getTimeStampByUTC(request.getTaskDateTo()));
            task.setTaskDateFrom(DateUtils.getTimeStampByUTC(request.getTaskDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        if ("子活动".equals(request.getParentType())) {
            task.setActSubId(request.getParentId());
        }
        if (1 == taskMapper.insertSelective(task)) {
            // 插入成功才更新负责人
            List<TaskChargerDto> taskChargerList = request.getPersonChargers();
            taskChargerList.stream()
                    .filter(e -> e.getStatus())
                    .map(r -> BeanUtils.copyBean(r, TaskCharger.class))
                    .peek(e -> e.setInstId(personMemberMapper.selectByPrimaryKey(e.getPersonId()).getInstId()))
                    .peek(e -> e.setTaskId(task.getTaskId()))
                    .collect(Collectors.toList()).forEach(taskCharger -> taskChargerMapper.insertSelective(taskCharger));
        } else {
            throw new BusinessException("插入失败！");
        }
        return task.getTaskId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyTask(TaskRequest request) {
        Task task = BeanUtils.copyBean(request, Task.class);
        try {
            task.setTaskDateTo(DateUtils.getTimeStampByUTC(request.getTaskDateTo()));
            task.setTaskDateFrom(DateUtils.getTimeStampByUTC(request.getTaskDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        TaskCharger taskCharger = new TaskCharger();
        taskCharger.setTaskChargerId(taskChargerMapper.getMainTaskCharger(request.getTaskId()).getTaskChargerId());
        taskCharger.setTaskId(request.getTaskId());
        taskCharger.setPersonId(request.getPersonId());
        taskCharger.setTaskChargerDuty(request.getDuty());
        taskMapper.updateByPrimaryKeySelective(task);
        taskChargerMapper.updateByPrimaryKeySelective(taskCharger);
        return true;
    }

    @Override
    public int deleteTask(Long taskId) {
        Task task = new Task() {
            {
                setTaskId(taskId);
                setStop(true);
            }
        };
        return taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public int addTaskCheckPoint(TaskCheckPointRequest request) {
        TaskCheckPoint taskCheckPoint = BeanUtils.copyBean(request, TaskCheckPoint.class);
        try {
            taskCheckPoint.setTaskCheckPointDate(DateUtils.getTimeStampByUTC(request.getTaskCheckPointDate()));
        } catch (ParseException e) {
            throw new BusinessException("日期转化错误！");
        }
        return taskCheckPointMapper.insertSelective(taskCheckPoint);
    }

    @Override
    public int modifyTaskCheckPoint(TaskCheckPointRequest request) {
        TaskCheckPoint taskCheckPoint = BeanUtils.copyBean(request, TaskCheckPoint.class);
        try {
            if (request.getTaskCheckPointDate() != null) {
                taskCheckPoint.setTaskCheckPointDate(DateUtils.getTimeStampByUTC(request.getTaskCheckPointDate()));
            }
        } catch (ParseException e) {
            throw new BusinessException("日期转化错误！");
        }
        return taskCheckPointMapper.updateByPrimaryKeySelective(taskCheckPoint);
    }

    @Override
    public int deleteTaskCheckPoint(Long taskCheckPointId) {
        return taskCheckPointMapper.deleteByPrimaryKey(taskCheckPointId);
    }

    @Override
    public List<TaskCheckPointVo> getTaskCheckPoint(Long taskId) {
        return taskCheckPointMapper.getByTaskId(taskId).stream()
                .map(e -> BeanUtils.copyBean(e, TaskCheckPointVo.class)).collect(Collectors.toList());
    }

    @Override
    public PagedResult<TaskVo> getOwnerTask(TaskQuery query, Long personId) {
        query.setPersonId(personId);
        PagedResult<TaskVo> pagedResult = new PagedResult<>();
        pagedResult.setPage(query.getPage());
        pagedResult.setSize(query.getSize());
        long count = taskChargerMapper.count(query);
        pagedResult.setTotal(count);
        if (count > 0) {
            List<TaskVo> taskVos = taskChargerMapper.getByPersonId(query).stream()
                    .map(taskCharger -> {
                        Task task = taskMapper.selectByPrimaryKey(taskCharger.getTaskId());
                        TaskVo taskVo = setTaskInfo(task);
                        taskVo.setDuty(taskCharger.getTaskChargerDuty());
                        taskVo.setPersonName(personMemberMapper.selectByPrimaryKey(taskChargerMapper.getMainTaskCharger(task.getTaskId()).getPersonId()).getName());
                        taskVo.setPriorityName(PriorityEnum.getByCode(task.getTaskPriority()).desc());
                        taskVo.setEditable(true);
                        Activity activity = activityMapper.selectByPrimaryKey(task.getActId());
                        taskVo.setActName(activity != null ? activity.getActName() : "");
                        return taskVo;
                    })
                    .collect(Collectors.toList());
            if (query.getSortByPriority() != null && query.getSortByPriority()) {
                List<TaskVo> results = taskVos.stream()
                        .sorted((p1, p2) -> p2.getTaskPriority().compareTo(p1.getTaskPriority()))
                        .collect(Collectors.toList());
                pagedResult.setItems(results);
            } else {
                pagedResult.setItems(taskVos);
            }
        } else {
            pagedResult.setItems(Collections.emptyList());
        }
        return pagedResult;
    }

    @Override
    public PagedResult<TaskVo> getAllTask(TaskQuery query) {
        PagedResult<TaskVo> pagedResult = new PagedResult<>();
        pagedResult.setPage(query.getPage());
        pagedResult.setSize(query.getSize());
        long count = taskMapper.countAllTasks(query);
        pagedResult.setTotal(count);
        if (count > 0) {
            List<TaskVo> results = new ArrayList<>();
            taskMapper.getAllTasks(query).forEach(task -> {
                TaskVo taskVo = setTaskInfo(task);
                TaskCharger mainTaskCharger = taskChargerMapper.getMainTaskCharger(task.getTaskId());
                if (mainTaskCharger != null) {
                    taskVo.setDuty(mainTaskCharger.getTaskChargerDuty());
                    taskVo.setPersonName(personMemberMapper.selectByPrimaryKey(mainTaskCharger.getPersonId()).getName());
                }
                taskVo.setPriorityName(PriorityEnum.getByCode(task.getTaskPriority()).desc());
                taskVo.setEditable(false);
                Activity activity = activityMapper.selectByPrimaryKey(task.getActId());
                taskVo.setActName(activity != null ? activity.getActName() : "");
                results.add(taskVo);
            });
            pagedResult.setItems(results);
        } else {
            pagedResult.setItems(Collections.emptyList());
        }
        return pagedResult;
    }

    @Override
    public boolean addTaskExec(TaskExecRequest request) {
        TaskExec taskExec = BeanUtils.copyBean(request, TaskExec.class);
        if (taskExec.getTaskExecStatus() == null) {
            taskExec.setTaskExecStatus(1L);
        }
        try {
            taskExec.setTaskExecDate(DateUtils.getTimeStampByUTC(request.getTaskExecDate()));
        } catch (ParseException e) {
            throw new BusinessException("日期转化错误！");
        }
        return 1 == taskExecMapper.insertSelective(taskExec);
    }

    @Override
    public boolean modifyTaskExec(TaskExecRequest request) {
        TaskExec taskExec = BeanUtils.copyBean(request, TaskExec.class);
        try {
            taskExec.setTaskExecDate(DateUtils.getTimeStampByUTC(request.getTaskExecDate()));
        } catch (ParseException e) {
            throw new BusinessException("日期转化错误！");
        }
        return 1 == taskExecMapper.updateByPrimaryKeySelective(taskExec);
    }

    @Override
    public List<TaskExecVo> getTaskExec(Long taskId) {

        return taskExecMapper.getByTaskId(taskId).stream().map(e -> {
            TaskExecVo taskExecVo = BeanUtils.copyBean(e, TaskExecVo.class);
            taskExecVo.setTaskExecDate(DateUtils.getDateStrByTimestamp(e.getTaskExecDate()));
            taskExecVo.setTaskExecStatusName(activityStatusMapper.selectByPrimaryKey(e.getTaskExecStatus()).getActStatusName());
            return taskExecVo;
        }).collect(Collectors.toList());
    }

    private TaskVo setTaskInfo(Task task) {
        return convertTaskToVo(task, activityStatusMapper);
    }


}
