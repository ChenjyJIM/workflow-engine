package com.graduate.engine.model;

public class ActivitySub {
    private Integer actSubId;

    private Integer actId;

    private Integer instId;

    private Integer instSubId;

    private String actSubName;

    private String actSubShort;

    private String actSubEngName;

    private Byte industyId;

    private Integer actSubDateFrom;

    private Integer actSubDataTo;

    private String actSubAddress;

    private Byte actSubPriority;

    private Byte actSubStatusId;

    private String actSubMemo;

    private Integer actSubSplit;

    private Integer actSubMerge;

    private Integer actSubFatherId;

    private String actSubRestartMemo;

    private Boolean stop;

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

    public String getActSubName() {
        return actSubName;
    }

    public void setActSubName(String actSubName) {
        this.actSubName = actSubName == null ? null : actSubName.trim();
    }

    public String getActSubShort() {
        return actSubShort;
    }

    public void setActSubShort(String actSubShort) {
        this.actSubShort = actSubShort == null ? null : actSubShort.trim();
    }

    public String getActSubEngName() {
        return actSubEngName;
    }

    public void setActSubEngName(String actSubEngName) {
        this.actSubEngName = actSubEngName == null ? null : actSubEngName.trim();
    }

    public Byte getIndustyId() {
        return industyId;
    }

    public void setIndustyId(Byte industyId) {
        this.industyId = industyId;
    }

    public Integer getActSubDateFrom() {
        return actSubDateFrom;
    }

    public void setActSubDateFrom(Integer actSubDateFrom) {
        this.actSubDateFrom = actSubDateFrom;
    }

    public Integer getActSubDataTo() {
        return actSubDataTo;
    }

    public void setActSubDataTo(Integer actSubDataTo) {
        this.actSubDataTo = actSubDataTo;
    }

    public String getActSubAddress() {
        return actSubAddress;
    }

    public void setActSubAddress(String actSubAddress) {
        this.actSubAddress = actSubAddress == null ? null : actSubAddress.trim();
    }

    public Byte getActSubPriority() {
        return actSubPriority;
    }

    public void setActSubPriority(Byte actSubPriority) {
        this.actSubPriority = actSubPriority;
    }

    public Byte getActSubStatusId() {
        return actSubStatusId;
    }

    public void setActSubStatusId(Byte actSubStatusId) {
        this.actSubStatusId = actSubStatusId;
    }

    public String getActSubMemo() {
        return actSubMemo;
    }

    public void setActSubMemo(String actSubMemo) {
        this.actSubMemo = actSubMemo == null ? null : actSubMemo.trim();
    }

    public Integer getActSubSplit() {
        return actSubSplit;
    }

    public void setActSubSplit(Integer actSubSplit) {
        this.actSubSplit = actSubSplit;
    }

    public Integer getActSubMerge() {
        return actSubMerge;
    }

    public void setActSubMerge(Integer actSubMerge) {
        this.actSubMerge = actSubMerge;
    }

    public Integer getActSubFatherId() {
        return actSubFatherId;
    }

    public void setActSubFatherId(Integer actSubFatherId) {
        this.actSubFatherId = actSubFatherId;
    }

    public String getActSubRestartMemo() {
        return actSubRestartMemo;
    }

    public void setActSubRestartMemo(String actSubRestartMemo) {
        this.actSubRestartMemo = actSubRestartMemo == null ? null : actSubRestartMemo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}