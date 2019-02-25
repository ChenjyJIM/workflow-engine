package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActMilestone {
    private Integer milestoneId;

    private Integer actId;

    private String milestoneName;

    private String milestoneMemo;

    private Integer milestoneFrom;

    private Integer milestoneTo;

    private String milestoneReport;

    private String milestoneMonitor;
}