package com.graduate.engine.model;

public class TaskCheckPoint {
    private Integer taskCheckPointId;

    private Integer taskId;

    private String taskCheckPointName;

    private String taskCheckPointMemo;

    private Integer taskCheckPointDate;

    public Integer getTaskCheckPointId() {
        return taskCheckPointId;
    }

    public void setTaskCheckPointId(Integer taskCheckPointId) {
        this.taskCheckPointId = taskCheckPointId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskCheckPointName() {
        return taskCheckPointName;
    }

    public void setTaskCheckPointName(String taskCheckPointName) {
        this.taskCheckPointName = taskCheckPointName == null ? null : taskCheckPointName.trim();
    }

    public String getTaskCheckPointMemo() {
        return taskCheckPointMemo;
    }

    public void setTaskCheckPointMemo(String taskCheckPointMemo) {
        this.taskCheckPointMemo = taskCheckPointMemo == null ? null : taskCheckPointMemo.trim();
    }

    public Integer getTaskCheckPointDate() {
        return taskCheckPointDate;
    }

    public void setTaskCheckPointDate(Integer taskCheckPointDate) {
        this.taskCheckPointDate = taskCheckPointDate;
    }
}