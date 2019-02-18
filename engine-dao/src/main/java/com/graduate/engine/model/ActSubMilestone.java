package com.graduate.engine.model;

public class ActSubMilestone {
    private Integer subMilestoneId;

    private Integer actSubId;

    private String subMilestoneName;

    private String subMilestoneMemo;

    private Integer subMilestoneFrom;

    private Integer subMilestoneTo;

    private String subMilestoneReport;

    private String subMilestoneMonitor;

    public Integer getSubMilestoneId() {
        return subMilestoneId;
    }

    public void setSubMilestoneId(Integer subMilestoneId) {
        this.subMilestoneId = subMilestoneId;
    }

    public Integer getActSubId() {
        return actSubId;
    }

    public void setActSubId(Integer actSubId) {
        this.actSubId = actSubId;
    }

    public String getSubMilestoneName() {
        return subMilestoneName;
    }

    public void setSubMilestoneName(String subMilestoneName) {
        this.subMilestoneName = subMilestoneName == null ? null : subMilestoneName.trim();
    }

    public String getSubMilestoneMemo() {
        return subMilestoneMemo;
    }

    public void setSubMilestoneMemo(String subMilestoneMemo) {
        this.subMilestoneMemo = subMilestoneMemo == null ? null : subMilestoneMemo.trim();
    }

    public Integer getSubMilestoneFrom() {
        return subMilestoneFrom;
    }

    public void setSubMilestoneFrom(Integer subMilestoneFrom) {
        this.subMilestoneFrom = subMilestoneFrom;
    }

    public Integer getSubMilestoneTo() {
        return subMilestoneTo;
    }

    public void setSubMilestoneTo(Integer subMilestoneTo) {
        this.subMilestoneTo = subMilestoneTo;
    }

    public String getSubMilestoneReport() {
        return subMilestoneReport;
    }

    public void setSubMilestoneReport(String subMilestoneReport) {
        this.subMilestoneReport = subMilestoneReport == null ? null : subMilestoneReport.trim();
    }

    public String getSubMilestoneMonitor() {
        return subMilestoneMonitor;
    }

    public void setSubMilestoneMonitor(String subMilestoneMonitor) {
        this.subMilestoneMonitor = subMilestoneMonitor == null ? null : subMilestoneMonitor.trim();
    }
}