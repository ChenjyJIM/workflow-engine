package com.graduate.engine.model;

public class Task {
    private Integer taskId;

    private Integer actSubId;

    private Integer actId;

    private String taskName;

    private String taskShort;

    private String taskEngName;

    private Integer taskDateFrom;

    private Integer taskDateTo;

    private Byte taskPriority;

    private Byte taskStatusId;

    private String taskMemo;

    private String taskRestartMemo;

    private Boolean stop;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getActSubId() {
        return actSubId;
    }

    public void setActSubId(Integer actSubId) {
        this.actSubId = actSubId;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskShort() {
        return taskShort;
    }

    public void setTaskShort(String taskShort) {
        this.taskShort = taskShort == null ? null : taskShort.trim();
    }

    public String getTaskEngName() {
        return taskEngName;
    }

    public void setTaskEngName(String taskEngName) {
        this.taskEngName = taskEngName == null ? null : taskEngName.trim();
    }

    public Integer getTaskDateFrom() {
        return taskDateFrom;
    }

    public void setTaskDateFrom(Integer taskDateFrom) {
        this.taskDateFrom = taskDateFrom;
    }

    public Integer getTaskDateTo() {
        return taskDateTo;
    }

    public void setTaskDateTo(Integer taskDateTo) {
        this.taskDateTo = taskDateTo;
    }

    public Byte getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Byte taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Byte getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(Byte taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    public String getTaskMemo() {
        return taskMemo;
    }

    public void setTaskMemo(String taskMemo) {
        this.taskMemo = taskMemo == null ? null : taskMemo.trim();
    }

    public String getTaskRestartMemo() {
        return taskRestartMemo;
    }

    public void setTaskRestartMemo(String taskRestartMemo) {
        this.taskRestartMemo = taskRestartMemo == null ? null : taskRestartMemo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}