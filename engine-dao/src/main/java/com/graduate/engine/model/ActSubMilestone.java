package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActSubMilestone {
    private Integer subMilestoneId;

    private Integer actSubId;

    private String subMilestoneName;

    private String subMilestoneMemo;

    private Integer subMilestoneFrom;

    private Integer subMilestoneTo;

    private String subMilestoneReport;

    private String subMilestoneMonitor;

}