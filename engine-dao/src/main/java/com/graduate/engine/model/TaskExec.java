package com.graduate.engine.model;

public class TaskExec {
    private Integer taskExecId;

    private Integer taskId;

    private String taskExecName;

    private Byte taskExecStatus;

    private String taskExecReport;

    private String taskExecMonitor;

    private String taskExecDocs;

    private Double taskExecRate;

    private Integer taskExecDate;

    public Integer getTaskExecId() {
        return taskExecId;
    }

    public void setTaskExecId(Integer taskExecId) {
        this.taskExecId = taskExecId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskExecName() {
        return taskExecName;
    }

    public void setTaskExecName(String taskExecName) {
        this.taskExecName = taskExecName == null ? null : taskExecName.trim();
    }

    public Byte getTaskExecStatus() {
        return taskExecStatus;
    }

    public void setTaskExecStatus(Byte taskExecStatus) {
        this.taskExecStatus = taskExecStatus;
    }

    public String getTaskExecReport() {
        return taskExecReport;
    }

    public void setTaskExecReport(String taskExecReport) {
        this.taskExecReport = taskExecReport == null ? null : taskExecReport.trim();
    }

    public String getTaskExecMonitor() {
        return taskExecMonitor;
    }

    public void setTaskExecMonitor(String taskExecMonitor) {
        this.taskExecMonitor = taskExecMonitor == null ? null : taskExecMonitor.trim();
    }

    public String getTaskExecDocs() {
        return taskExecDocs;
    }

    public void setTaskExecDocs(String taskExecDocs) {
        this.taskExecDocs = taskExecDocs == null ? null : taskExecDocs.trim();
    }

    public Double getTaskExecRate() {
        return taskExecRate;
    }

    public void setTaskExecRate(Double taskExecRate) {
        this.taskExecRate = taskExecRate;
    }

    public Integer getTaskExecDate() {
        return taskExecDate;
    }

    public void setTaskExecDate(Integer taskExecDate) {
        this.taskExecDate = taskExecDate;
    }
}