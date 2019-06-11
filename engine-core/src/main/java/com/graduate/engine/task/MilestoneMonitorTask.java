package com.graduate.engine.task;

import com.graduate.engine.service.AutoTaskService;
import com.graduate.engine.service.impl.MilestoneTaskServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 后续维护可接入日志相关目前仅打印到控制台
 *
 * @author jimmy
 */
@Component
public class MilestoneMonitorTask {
    private Logger logger = LoggerFactory.getLogger(MilestoneMonitorTask.class);


    @Resource
    private MilestoneTaskServiceFactory milestoneTaskServiceFactory;

    /**
     * 相隔一天以外每天一次
     * 0 0 12 * * ?
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void commonTask() {
        logger.info("commonTask start..");
        execute(1);
        logger.info("commonTask end..");
    }

    /**
     * 时间在一天以内每天多次
     * 0 0 9,12,15,18 * * ?
     */
    @Scheduled(cron = "0 0 9,12,15,18 * * ?")
    public void emergencyTask() {
        logger.info("emergencyTask start..");
        execute(2);
        logger.info("emergencyTask end..");
    }

    private void execute(Integer type) {
        AutoTaskService autoTaskService = milestoneTaskServiceFactory.getAutoTaskService(type);
        if (autoTaskService == null) {
            logger.warn("autoTaskService is null");
            return;
        }
        autoTaskService.monitor();

        logger.info("auto task execute end");
    }
}
