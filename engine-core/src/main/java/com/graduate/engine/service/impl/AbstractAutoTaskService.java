package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.*;
import com.graduate.engine.model.*;
import com.graduate.engine.service.MessageService;

import java.util.List;

public abstract class AbstractAutoTaskService {

    private static final String REFIX_MSG = "--若已检查，请忽略。";

    protected void sendTaskMsg(List<TaskCheckPoint> taskCheckPoints, TaskMapper taskMapper, TaskChargerMapper taskChargerMapper, LoginMapper loginMapper, MessageService messageService, String autoMonitorTitle, String autoTaskMonitorContent) {
        taskCheckPoints.forEach(taskCheckPoint -> {
            Task task = taskMapper.selectByPrimaryKey(taskCheckPoint.getTaskId());
            TaskCharger mainTaskCharger = taskChargerMapper.getMainTaskCharger(taskCheckPoint.getTaskId());
            Login login = loginMapper.getLoginIdByPersonId(mainTaskCharger.getPersonId());
            if (login == null) {
                return;
            }
            Long[] loginIds = {login.getLoginId()};
            messageService.sendMessageToUsers(autoMonitorTitle, autoTaskMonitorContent + task.getTaskName() + REFIX_MSG, loginIds);
        });
    }

    protected void sendActSubMsg(List<ActSubMilestone> actSubMilestones, ActSubChargerMapper actSubChargerMapper, ActivitySubMapper activitySubMapper, LoginMapper loginMapper, MessageService messageService, String autoMonitorTitle, String autoActMonitorContent) {
        actSubMilestones.forEach(actSubMilestone -> {
            // 仅给核心负责人发送推送
            ActSubCharger mainSubCharger = actSubChargerMapper.getMainSubCharger(actSubMilestone.getActSubId());
            ActivitySub activitySub = activitySubMapper.selectByPrimaryKey(actSubMilestone.getActSubId());
            Login login = loginMapper.getLoginIdByPersonId(mainSubCharger.getPersonId());
            if (login == null) {
                return;
            }
            Long[] loginIds = {login.getLoginId()};
            messageService.sendMessageToUsers(autoMonitorTitle, autoActMonitorContent + activitySub.getActSubName() + REFIX_MSG, loginIds);
        });
    }

    protected void sendActMsg(List<ActMilestone> actMilestones, ActivityMapper activityMapper, LoginMapper loginMapper, MessageService messageService, String autoMonitorTitle, String autoActMonitorContent) {
        actMilestones.forEach(actMilestone -> {
            Activity activity = activityMapper.selectByPrimaryKey(actMilestone.getActId());
            Login login = loginMapper.getLoginIdByPersonId(activity.getPersonId());
            if (login == null) {
                return;
            }
            Long[] loginIds = {login.getLoginId()};
            messageService.sendMessageToUsers(autoMonitorTitle, autoActMonitorContent + activity.getActName() + REFIX_MSG, loginIds);
        });
    }
}
