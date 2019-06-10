package com.graduate.engine.request;

import lombok.Data;

/**
 * 里程碑相关请求模板
 *
 * @author jimmy
 */
@Data
public class MilestoneRequest {

    /**
     * （子）活动id
     */
    private Long id;

    private Long milestoneId;


    private String milestoneName;

    private String milestoneMemo;

    private String milestoneFrom;

    private String milestoneTo;

    private String milestoneReport;

    private String milestoneMonitor;

    /**
     * 用来区分是活动还是子活动
     */
    private String type;
}
