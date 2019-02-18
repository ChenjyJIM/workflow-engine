package com.graduate.engine.model;

public class ActSubCharger {
    private Integer actSubChargerId;

    private Integer actSubId;

    private Integer personId;

    private Integer actSubChargerOrder;

    private Boolean actSubChargerEditer;

    private Integer instId;

    private String actSubChargerDuty;

    private Boolean stop;

    public Integer getActSubChargerId() {
        return actSubChargerId;
    }

    public void setActSubChargerId(Integer actSubChargerId) {
        this.actSubChargerId = actSubChargerId;
    }

    public Integer getActSubId() {
        return actSubId;
    }

    public void setActSubId(Integer actSubId) {
        this.actSubId = actSubId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getActSubChargerOrder() {
        return actSubChargerOrder;
    }

    public void setActSubChargerOrder(Integer actSubChargerOrder) {
        this.actSubChargerOrder = actSubChargerOrder;
    }

    public Boolean getActSubChargerEditer() {
        return actSubChargerEditer;
    }

    public void setActSubChargerEditer(Boolean actSubChargerEditer) {
        this.actSubChargerEditer = actSubChargerEditer;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getActSubChargerDuty() {
        return actSubChargerDuty;
    }

    public void setActSubChargerDuty(String actSubChargerDuty) {
        this.actSubChargerDuty = actSubChargerDuty == null ? null : actSubChargerDuty.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}