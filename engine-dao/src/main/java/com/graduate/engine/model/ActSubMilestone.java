package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActSubMilestone {
    private Long subMilestoneId;

    private Long actSubId;

    private String subMilestoneName;

    private String subMilestoneMemo;

    private Long subMilestoneFrom;

    private Long subMilestoneTo;

    private String subMilestoneReport;

    private String subMilestoneMonitor;

}
