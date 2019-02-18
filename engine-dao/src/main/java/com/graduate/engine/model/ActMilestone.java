package com.graduate.engine.model;

public class ActMilestone {
    private Integer milestoneId;

    private Integer actId;

    private String milestoneName;

    private String milestoneMemo;

    private Integer milestoneFrom;

    private Integer milestoneTo;

    private String milestoneReport;

    private String milestoneMonitor;

    public Integer getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Integer milestoneId) {
        this.milestoneId = milestoneId;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName == null ? null : milestoneName.trim();
    }

    public String getMilestoneMemo() {
        return milestoneMemo;
    }

    public void setMilestoneMemo(String milestoneMemo) {
        this.milestoneMemo = milestoneMemo == null ? null : milestoneMemo.trim();
    }

    public Integer getMilestoneFrom() {
        return milestoneFrom;
    }

    public void setMilestoneFrom(Integer milestoneFrom) {
        this.milestoneFrom = milestoneFrom;
    }

    public Integer getMilestoneTo() {
        return milestoneTo;
    }

    public void setMilestoneTo(Integer milestoneTo) {
        this.milestoneTo = milestoneTo;
    }

    public String getMilestoneReport() {
        return milestoneReport;
    }

    public void setMilestoneReport(String milestoneReport) {
        this.milestoneReport = milestoneReport == null ? null : milestoneReport.trim();
    }

    public String getMilestoneMonitor() {
        return milestoneMonitor;
    }

    public void setMilestoneMonitor(String milestoneMonitor) {
        this.milestoneMonitor = milestoneMonitor == null ? null : milestoneMonitor.trim();
    }
}