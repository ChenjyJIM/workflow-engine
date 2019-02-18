package com.graduate.engine.model;

public class Activity {
    private Integer actId;

    private Integer instId;

    private Integer instSubId;

    private Integer personId;

    private String actName;

    private String actShort;

    private String actEngName;

    private Byte industyId;

    private Integer actDate;

    private Integer actDateFrom;

    private Integer actDateTo;

    private String actAddress;

    private Byte actPriority;

    private Byte actStatusId;

    private String actMemo;

    private Boolean stop;

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getInstSubId() {
        return instSubId;
    }

    public void setInstSubId(Integer instSubId) {
        this.instSubId = instSubId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName == null ? null : actName.trim();
    }

    public String getActShort() {
        return actShort;
    }

    public void setActShort(String actShort) {
        this.actShort = actShort == null ? null : actShort.trim();
    }

    public String getActEngName() {
        return actEngName;
    }

    public void setActEngName(String actEngName) {
        this.actEngName = actEngName == null ? null : actEngName.trim();
    }

    public Byte getIndustyId() {
        return industyId;
    }

    public void setIndustyId(Byte industyId) {
        this.industyId = industyId;
    }

    public Integer getActDate() {
        return actDate;
    }

    public void setActDate(Integer actDate) {
        this.actDate = actDate;
    }

    public Integer getActDateFrom() {
        return actDateFrom;
    }

    public void setActDateFrom(Integer actDateFrom) {
        this.actDateFrom = actDateFrom;
    }

    public Integer getActDateTo() {
        return actDateTo;
    }

    public void setActDateTo(Integer actDateTo) {
        this.actDateTo = actDateTo;
    }

    public String getActAddress() {
        return actAddress;
    }

    public void setActAddress(String actAddress) {
        this.actAddress = actAddress == null ? null : actAddress.trim();
    }

    public Byte getActPriority() {
        return actPriority;
    }

    public void setActPriority(Byte actPriority) {
        this.actPriority = actPriority;
    }

    public Byte getActStatusId() {
        return actStatusId;
    }

    public void setActStatusId(Byte actStatusId) {
        this.actStatusId = actStatusId;
    }

    public String getActMemo() {
        return actMemo;
    }

    public void setActMemo(String actMemo) {
        this.actMemo = actMemo == null ? null : actMemo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}