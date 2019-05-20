package com.graduate.engine.model;

import com.graduate.engine.model.viewobject.MilestoneVo;
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

    public MilestoneVo packetVo() {
        MilestoneVo result = new MilestoneVo();
        result.setMilestoneName(this.subMilestoneName);
        result.setMilestoneId(this.subMilestoneId);
        result.setMilestoneFrom(this.subMilestoneFrom);
        result.setMilestoneTo(this.subMilestoneTo);
        result.setMilestoneMemo(this.subMilestoneMemo);
        result.setMilestoneReport(this.subMilestoneReport);
        result.setMilestoneMonitor(this.subMilestoneMonitor);
        return result;

    }
}
