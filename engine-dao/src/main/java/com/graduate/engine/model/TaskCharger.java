package com.graduate.engine.model;

public class TaskCharger {
    private Integer taskChargerId;

    private Integer taskId;

    private Integer personId;

    private Byte taskChargerOrder;

    private Boolean taskChargerEditer;

    private Integer instId;

    private String taskChargerDuty;

    private Boolean stop;

    public Integer getTaskChargerId() {
        return taskChargerId;
    }

    public void setTaskChargerId(Integer taskChargerId) {
        this.taskChargerId = taskChargerId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Byte getTaskChargerOrder() {
        return taskChargerOrder;
    }

    public void setTaskChargerOrder(Byte taskChargerOrder) {
        this.taskChargerOrder = taskChargerOrder;
    }

    public Boolean getTaskChargerEditer() {
        return taskChargerEditer;
    }

    public void setTaskChargerEditer(Boolean taskChargerEditer) {
        this.taskChargerEditer = taskChargerEditer;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getTaskChargerDuty() {
        return taskChargerDuty;
    }

    public void setTaskChargerDuty(String taskChargerDuty) {
        this.taskChargerDuty = taskChargerDuty == null ? null : taskChargerDuty.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}