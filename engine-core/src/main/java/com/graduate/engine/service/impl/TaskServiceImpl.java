package com.graduate.engine.service.impl;

import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.PersonMemberMapper;
import com.graduate.engine.mapper.TaskChargerMapper;
import com.graduate.engine.mapper.TaskMapper;
import com.graduate.engine.model.Task;
import com.graduate.engine.model.TaskCharger;
import com.graduate.engine.model.viewobject.TaskChargerDto;
import com.graduate.engine.request.TaskRequest;
import com.graduate.engine.service.TaskService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务相关服务
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

    @Override
    public Long addTask(TaskRequest request) {
        Task task = BeanUtils.copyBean(request, Task.class);
        task.setTaskStatusId(0);
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
        taskCharger.setTaskId(request.getTaskId());
        taskCharger.setPersonId(request.getPersonId());
        taskCharger.setTaskChargerDuty(request.getDuty());
        taskMapper.updateByPrimaryKeySelective(task);
        taskChargerMapper.updateByPrimaryKeySelective(taskCharger);
        return true ;
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
}
