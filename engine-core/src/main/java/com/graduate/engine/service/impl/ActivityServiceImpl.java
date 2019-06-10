package com.graduate.engine.service.impl;

import com.graduate.engine.enums.PriorityEnum;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.*;
import com.graduate.engine.model.*;
import com.graduate.engine.model.viewobject.*;
import com.graduate.engine.request.ActivityQuery;
import com.graduate.engine.request.ActivityRequest;
import com.graduate.engine.request.ActivitySubRequest;
import com.graduate.engine.response.PagedResult;
import com.graduate.engine.service.ActivityService;
import com.graduate.engine.service.MessageService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.utils.TreeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.graduate.engine.service.impl.TaskServiceImpl.convertTaskToVo;

@Service
public class ActivityServiceImpl implements ActivityService {

    private static final String PUBLISH_MSG_TITLE = "活动发布通知";

    private static final String PUBLISH_CONTENT = "有新的活动已发布，请前往活动管理--> 所有活动中查看:";
    @Resource
    private LoginMapper loginMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivitySubMapper activitySubMapper;

    @Resource
    private PersonMemberMapper personMemberMapper;

    @Resource
    private ActSubChargerMapper actSubChargerMapper;

    @Resource
    private IndustryMapper industryMapper;

    @Resource
    private ActivityStatusMapper activityStatusMapper;

    @Resource
    private InstituteMapper instituteMapper;

    @Resource
    private InstituteSubMapper instituteSubMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private ActMilestoneMapper actMilestoneMapper;

    @Resource
    private ActSubMilestoneMapper actSubMilestoneMapper;

    @Resource
    private TaskCheckPointMapper taskCheckPointMapper;

    @Resource
    private TaskChargerMapper taskChargerMapper;

    @Resource
    private MessageService messageService;

    @Override
    public Long addActivity(ActivityRequest request) {
        Activity activity = BeanUtils.copyBean(request, Activity.class);
        activity.setActDate(DateUtils.getCurrentMillSecondsTimestamp() / 1000);
        activity.setActStatusId(1L);
        try {
            activity.setActDateTo(DateUtils.getTimeStampByUTC(request.getActDateTo()));
            activity.setActDateFrom(DateUtils.getTimeStampByUTC(request.getActDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        List<String> ids = request.getIds();
        activity.setInstId(Long.parseLong(ids.get(0)));
        activity.setInstSubId(Long.parseLong(ids.get(1)));
        activityMapper.insertSelective(activity);
        return activity.getActId();
    }

    @Override
    public int modifyActivity(ActivityRequest request) {
        Activity activity = BeanUtils.copyBean(request, Activity.class);
        try {
            activity.setActDateTo(DateUtils.getTimeStampByUTC(request.getActDateTo()));
            activity.setActDateFrom(DateUtils.getTimeStampByUTC(request.getActDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public int deleteActivity(Long actId) {
        Activity activity = new Activity() {
            {
                setActId(actId);
                // 逻辑删除
                setStop(true);
            }
        };
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean publishActivity(Long actId) {
        Activity activity = activityMapper.selectByPrimaryKey(actId);
        Login login = loginMapper.getLoginIdByPersonId(activity.getPersonId());
        Long[] loginIds = {login.getLoginId()};
        messageService.sendMessageToUsers(PUBLISH_MSG_TITLE, PUBLISH_CONTENT + "活动名：" + activity.getActName() + "    活动id：" + actId, loginIds);
        return 1 == activityMapper.updateByPrimaryKeySelective(new Activity() {
            {
                setActId(actId);
                setPublish(true);
            }
        });
    }

    @Override
    public Long addSubActivity(ActivitySubRequest request) {
        ActivitySub activitySub = BeanUtils.copyBean(request, ActivitySub.class);
        activitySub.setActSubStatusId(1L);
        try {
            activitySub.setActSubDateTo(DateUtils.getTimeStampByUTC(request.getActSubDateTo()));
            activitySub.setActSubDateFrom(DateUtils.getTimeStampByUTC(request.getActSubDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        List<String> ids = request.getIds();
        activitySub.setInstId(Long.parseLong(ids.get(0)));
        activitySub.setInstSubId(Long.parseLong(ids.get(1)));
        if ("子活动".equals(request.getParentType())) {
            activitySub.setActSubFatherId(request.getParentId());
        }
        // 设置总活动根节点id
        activitySub.setActId(request.getActId());
        if (1 == activitySubMapper.insertSelective(activitySub)) {
            // 插入成功才更新负责人
            List<ActivitySubDto> actSubChargerList = request.getPersonChargers();
            actSubChargerList.stream()
                    .filter(e -> e.getStatus())
                    .map(r -> BeanUtils.copyBean(r, ActSubCharger.class))
                    .peek(e -> e.setInstId(personMemberMapper.selectByPrimaryKey(e.getPersonId()).getInstId()))
                    .peek(e -> e.setActSubId(activitySub.getActSubId()))
                    .collect(Collectors.toList()).forEach(actSubCharger -> actSubChargerMapper.insertSelective(actSubCharger));
        } else {
            throw new BusinessException("插入失败！");
        }
        return activitySub.getActSubId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyActivitySub(ActivitySubRequest request) {
        ActivitySub activitySub = BeanUtils.copyBean(request, ActivitySub.class);
        try {
            activitySub.setActSubDateTo(DateUtils.getTimeStampByUTC(request.getActSubDateTo()));
            activitySub.setActSubDateFrom(DateUtils.getTimeStampByUTC(request.getActSubDateFrom()));
        } catch (Exception e) {
            throw new BusinessException("日期转化解析失败！");
        }
        ActSubCharger actSubCharger = new ActSubCharger();
        actSubCharger.setActSubChargerId(actSubChargerMapper.getMainSubCharger(request.getActSubId()).getActSubChargerId());
        actSubCharger.setActSubId(request.getActSubId());
        actSubCharger.setPersonId(request.getPersonId());
        actSubCharger.setActSubChargerDuty(request.getDuty());
        activitySubMapper.updateByPrimaryKeySelective(activitySub);
        actSubChargerMapper.updateByPrimaryKeySelective(actSubCharger);
        return true;
    }

    @Override
    public int deleteActivitySub(Long actSubId) {
        ActivitySub activitySub = new ActivitySub() {
            {
                setActSubId(actSubId);
                // 逻辑删除
                setStop(true);
            }
        };
        return activitySubMapper.updateByPrimaryKeySelective(activitySub);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean publishActivitySub(Long actSubId) {
        List<Long> loginIds = new ArrayList<>();
        ActivitySub activitySub = activitySubMapper.selectByPrimaryKey(actSubId);
        actSubChargerMapper.getSubChargers(actSubId).forEach(actSubCharger ->
                loginIds.add(loginMapper.getLoginIdByPersonId(actSubCharger.getPersonId()).getLoginId()));
        messageService.sendMessageToUsers(PUBLISH_MSG_TITLE, PUBLISH_CONTENT + "子活动名：" + activitySub.getActSubName() + "    总活动id：" + actSubId,
                loginIds.toArray(new Long[loginIds.size()]));


        return 1 == activitySubMapper.updateByPrimaryKeySelective(new ActivitySub() {
            {
                setActSubId(actSubId);
                setPublish(true);
            }
        });
    }

    @Override
    public PagedResult<ActivityVo> queryActivity(ActivityQuery query) {
        PagedResult<ActivityVo> pagedResult = new PagedResult<>();
        pagedResult.setPage(query.getPage());
        pagedResult.setSize(query.getSize());
        List<String> ids = query.getIds();
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 2) {
                query.setInstId(Long.parseLong(ids.get(0)));
                query.setInstSubId(Long.parseLong(ids.get(1)));
            } else if (ids.size() == 1) {
                query.setInstId(Long.parseLong(ids.get(0)));
            }
        }
        long count = activityMapper.count(query);
        pagedResult.setTotal(count);
        if (count > 0) {
            List<ActivityVo> activityVos = new ArrayList<>();
            List<Activity> results = activityMapper.queryActivity(query);
            for (Activity result : results) {
                ActivityVo activityVo = BeanUtils.copyBean(result, ActivityVo.class);
                activityVo.setIndustryName(industryMapper.selectByPrimaryKey((result.getIndustryId())).getIndusName());
                activityVo.setActStatusName(activityStatusMapper.selectByPrimaryKey(result.getActStatusId()).getActStatusName());
                activityVo.setPersonName(personMemberMapper.selectByPrimaryKey(result.getPersonId()).getName());
                activityVo.setInstName(instituteMapper.selectByPrimaryKey(result.getInstId()).getInstName());
                activityVo.setInstSubName(instituteSubMapper.selectByPrimaryKey(result.getInstSubId()).getInstSubName());
                activityVo.setActDate(DateUtils.getDateStrByTimestamp(result.getActDate()));
                activityVo.setActDateFrom(DateUtils.getDateStrByTimestamp(result.getActDateFrom()));
                activityVo.setActDateTo(DateUtils.getDateStrByTimestamp(result.getActDateTo()));
                activityVos.add(activityVo);
            }
            pagedResult.setItems(activityVos);
        } else {
            pagedResult.setItems(Collections.<ActivityVo>emptyList());
        }
        return pagedResult;
    }

    @Override
    public ActivityTree getActivityById(Long actId) {
        // 总活动信息
        Activity activity = activityMapper.selectByPrimaryKey(actId);

        ActivityTree rootNode = BeanUtils.copyBean(activity, ActivityTree.class);
        rootNode.setId(activity.getActId());
        setExtraParam(rootNode);
        List<Task> tasks = taskMapper.getByActId(actId);
        // 设置总活动下的任务
        rootNode.setTasks(taskMapper.getByActId(actId).stream()
                .map(e -> setTaskInfo(e))
                .peek(e -> e.setTaskCheckPointVoList(BeanUtils.copyListWithBeans(taskCheckPointMapper.getByTaskId(e.getTaskId()), TaskCheckPointVo.class)))
                .peek(e -> {
                    TaskCharger mainTaskCharger = taskChargerMapper.getMainTaskCharger(e.getTaskId());
                    e.setPersonId(mainTaskCharger.getPersonId());
                    e.setDuty(mainTaskCharger.getTaskChargerDuty());
                    e.setPersonName(personMemberMapper.selectByPrimaryKey(e.getPersonId()).getName());
                })
                .collect(Collectors.toList()));

        rootNode.setMilestoneVos(BeanUtils.copyListWithBeans(actMilestoneMapper.getById(actId), MilestoneVo.class));
        rootNode.setChildren(Collections.emptyList());

        // 子活动信息
        List<ActivitySub> activityVos = activitySubMapper.getByActId(actId);
        List<ActivityTree> commonNode = activityVos.stream()
                .map(ActivitySub::packetActivityTree)
                .peek(e -> {
                    ActSubCharger subCharger = actSubChargerMapper.getMainSubCharger(e.getId());
                    e.setPersonId(subCharger.getPersonId());
                    e.setDuty(subCharger.getActSubChargerDuty());
                })
                .peek(this::setExtraParam)
                .peek(node -> node.setTasks(taskMapper.getByActSubId(node.getId()).stream()
                        .map(e -> setTaskInfo(e))
                        // 设置任务检查点
                        .peek(e -> e.setTaskCheckPointVoList(BeanUtils.copyListWithBeans(taskCheckPointMapper.getByTaskId(e.getTaskId()), TaskCheckPointVo.class)))
                        .peek(e -> {
                            TaskCharger mainTaskCharger = taskChargerMapper.getMainTaskCharger(e.getTaskId());
                            e.setPersonId(mainTaskCharger.getPersonId());
                            e.setDuty(mainTaskCharger.getTaskChargerDuty());
                            e.setPersonName(personMemberMapper.selectByPrimaryKey(e.getPersonId()).getName());
                        }).collect(Collectors.toList())))
                .peek(node -> node.setMilestoneVos(actSubMilestoneMapper.getById(node.getId()).stream().map(ActSubMilestone::packetVo).collect(Collectors.toList())))
                .collect(Collectors.toList());
        commonNode.add(rootNode);
        TreeUtils.createTree(commonNode, rootNode, "id", "parentId", "children");
        return rootNode;
    }

    @Override
    public TreeData getTreeData(Long actId) {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(12L);
//        messageService.sendMessageToUsers("测试消息", "测试发送的内容", ids.toArray(new Long[ids.size()]));

        // 总活动信息
        Activity activity = activityMapper.selectByPrimaryKey(actId);

        TreeData rootNode = new TreeData();
        rootNode.setId(activity.getActId());
        rootNode.setName(activity.getActName());
        rootNode.setPublish(activity.getPublish());
        rootNode.setStartTime(DateUtils.getDateStrByTimestamp(activity.getActDateFrom()).substring(0, 10));
        rootNode.setEndTime(DateUtils.getDateStrByTimestamp(activity.getActDateTo()).substring(0, 10));
        rootNode.setCharger(personMemberMapper.selectByPrimaryKey(activity.getPersonId()).getName());
        rootNode.setType("活动");
        rootNode.setPriority(PriorityEnum.getByCode(activity.getActPriority()).desc());
        rootNode.setIntroduction("这是介绍");
        rootNode.setChildren(Collections.emptyList());
        List<TreeData> commonData = new ArrayList<>();


        List<Task> tasks = taskMapper.getByActId(actId);
        convertTaskToTree(commonData, tasks);


//
        // 子活动信息
        List<ActivitySub> activityVos = activitySubMapper.getByActId(actId);
        List<TreeData> subs = activityVos.stream().map(ActivitySub::packetTreeData).peek(
                e -> e.setCharger(personMemberMapper.selectByPrimaryKey(actSubChargerMapper.getMainSubCharger(e.getId()).getPersonId()).getName())
        ).collect(Collectors.toList());
        commonData.addAll(subs);
        subs.forEach(sub -> {
            List<Task> subTasks = taskMapper.getByActSubId(sub.getId());
            convertTaskToTree(commonData, subTasks);
        });

        TreeUtils.createTree(commonData, rootNode, "id", "parentId", "children");
        return rootNode;
    }

    private void convertTaskToTree(List<TreeData> commonData, List<Task> subTasks) {
        subTasks.forEach(task -> {
            TreeData tempData = new TreeData();
            tempData.setId(task.getTaskId());
            tempData.setName(task.getTaskName());
            tempData.setStartTime(DateUtils.getDateStrByTimestamp(task.getTaskDateFrom()).substring(0, 10));
            tempData.setEndTime(DateUtils.getDateStrByTimestamp(task.getTaskDateTo()).substring(0, 10));
            tempData.setCharger(personMemberMapper.selectByPrimaryKey(taskChargerMapper.getMainTaskCharger(task.getTaskId()).getPersonId()).getName());
            tempData.setType("任务");
            tempData.setParentId(task.getActSubId() == null ? task.getActId() : task.getActSubId());
            tempData.setPriority(PriorityEnum.getByCode(task.getTaskPriority()).desc());
            tempData.setIntroduction("这是介绍");
            tempData.setChildren(Collections.emptyList());
            commonData.add(tempData);
        });
    }


    private TaskVo setTaskInfo(Task e) {
        return convertTaskToVo(e, activityStatusMapper);
    }

    private void setExtraParam(ActivityTree node) {
        node.setIndustryName(industryMapper.selectByPrimaryKey((node.getIndustryId())).getIndusName());
        node.setActStatusName(activityStatusMapper.selectByPrimaryKey(node.getActStatusId()).getActStatusName());
        node.setPersonName(personMemberMapper.selectByPrimaryKey(node.getPersonId()).getName());
        if (node.getActDate() != null) {
            node.setDate(DateUtils.getDateStrByTimestamp(node.getActDate()));
        }
        node.setDateFrom(DateUtils.getDateStrByTimestamp(node.getActDateFrom()));
        node.setDateTo(DateUtils.getDateStrByTimestamp(node.getActDateTo()));
    }

}
