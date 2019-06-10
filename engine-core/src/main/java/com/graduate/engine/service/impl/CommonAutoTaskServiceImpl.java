package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.*;
import com.graduate.engine.model.ActMilestone;
import com.graduate.engine.model.ActSubMilestone;
import com.graduate.engine.model.TaskCheckPoint;
import com.graduate.engine.service.CommonAutoTaskService;
import com.graduate.engine.service.MessageService;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CommonAutoTaskServiceImpl extends AbstractAutoTaskService implements CommonAutoTaskService {

    private static final String AUTO_MONITOR_TITLE = "里程碑提醒";

    private static final String AUTO_ACT_MONITOR_CONTENT = "您有一个里程碑信息即将到期请检查完成情况活动名：";

    private static final String AUTO_TASK_MONITOR_CONTENT = "您有一个任务检查点即将到期请检查完成情况任务名：";

    @Resource
    private ActMilestoneMapper actMilestoneMapper;

    @Resource
    private ActSubMilestoneMapper actSubMilestoneMapper;

    @Resource
    private TaskCheckPointMapper taskCheckPointMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivitySubMapper activitySubMapper;

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private ActSubChargerMapper actSubChargerMapper;

    @Resource
    private MessageService messageService;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskChargerMapper taskChargerMapper;

    @Override
    public void monitor() {
        Long now = DateUtils.getCurrentSecondTimestamp();
        List<ActMilestone> actMilestones = actMilestoneMapper.getActMilestoneByCondition(now, 1);
        sendActMsg(actMilestones, activityMapper, loginMapper, messageService, AUTO_MONITOR_TITLE, AUTO_ACT_MONITOR_CONTENT);
        List<ActSubMilestone> actSubMilestones = actSubMilestoneMapper.getActSubMilestoneByCondition(now, 1);
        sendActSubMsg(actSubMilestones, actSubChargerMapper, activitySubMapper, loginMapper, messageService, AUTO_MONITOR_TITLE, AUTO_ACT_MONITOR_CONTENT);
        List<TaskCheckPoint> taskCheckPoints = taskCheckPointMapper.getTaskCheckPointByCondition(now, 1);
        sendTaskMsg(taskCheckPoints, taskMapper, taskChargerMapper, loginMapper, messageService, AUTO_MONITOR_TITLE, AUTO_TASK_MONITOR_CONTENT);
    }


}
