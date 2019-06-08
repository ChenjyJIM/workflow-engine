package com.graduate.engine.service.impl;

import com.graduate.engine.service.AutoTaskService;
import com.graduate.engine.service.CommonAutoTaskService;
import com.graduate.engine.service.EmergencyAutoTaskService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MilestoneTaskServiceFactory {

    @Resource
    private CommonAutoTaskService commonAutoTaskService;

    @Resource
    private EmergencyAutoTaskService emergencyAutoTaskService;

    public AutoTaskService getAutoTaskService(Integer type) {
        if (Integer.valueOf(1).equals(type)) {
            return commonAutoTaskService;
        } else if (Integer.valueOf(2).equals(type)) {
            return emergencyAutoTaskService;
        }
        return null;
    }
}
