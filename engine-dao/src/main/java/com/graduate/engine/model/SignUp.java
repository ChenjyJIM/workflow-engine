package com.graduate.engine.model;

public class SignUp {
    private Integer signId;

    private String signName;

    private String signShort;

    private String signEngName;

    private Integer industryId;

    private Integer signDateFrom;

    private Integer signDateTo;

    private String memo;

    private Boolean stop;

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
    }

    public String getSignShort() {
        return signShort;
    }

    public void setSignShort(String signShort) {
        this.signShort = signShort == null ? null : signShort.trim();
    }

    public String getSignEngName() {
        return signEngName;
    }

    public void setSignEngName(String signEngName) {
        this.signEngName = signEngName == null ? null : signEngName.trim();
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getSignDateFrom() {
        return signDateFrom;
    }

    public void setSignDateFrom(Integer signDateFrom) {
        this.signDateFrom = signDateFrom;
    }

    public Integer getSignDateTo() {
        return signDateTo;
    }

    public void setSignDateTo(Integer signDateTo) {
        this.signDateTo = signDateTo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}