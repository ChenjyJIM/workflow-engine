package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActMilestone {
    private Long milestoneId;

    private Long actId;

    private String milestoneName;

    private String milestoneMemo;

    private Long milestoneFrom;

    private Long milestoneTo;

    private String milestoneReport;

    private String milestoneMonitor;
}
