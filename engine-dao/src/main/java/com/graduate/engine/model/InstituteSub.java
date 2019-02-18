package com.graduate.engine.model;

public class InstituteSub {
    private Integer instSubId;

    private String instSubName;

    private String instSubShort;

    private String instSubEngName;

    private Byte industyId;

    private String instSubAddress;

    private String instSubWebsite;

    private String instSubWechat;

    private String instSubQq;

    private String instSubOthers;

    private Integer instSubRegisterDate;

    private String instSubIntroduction;

    private String instSubMemo;

    private Boolean stop;

    public Integer getInstSubId() {
        return instSubId;
    }

    public void setInstSubId(Integer instSubId) {
        this.instSubId = instSubId;
    }

    public String getInstSubName() {
        return instSubName;
    }

    public void setInstSubName(String instSubName) {
        this.instSubName = instSubName == null ? null : instSubName.trim();
    }

    public String getInstSubShort() {
        return instSubShort;
    }

    public void setInstSubShort(String instSubShort) {
        this.instSubShort = instSubShort == null ? null : instSubShort.trim();
    }

    public String getInstSubEngName() {
        return instSubEngName;
    }

    public void setInstSubEngName(String instSubEngName) {
        this.instSubEngName = instSubEngName == null ? null : instSubEngName.trim();
    }

    public Byte getIndustyId() {
        return industyId;
    }

    public void setIndustyId(Byte industyId) {
        this.industyId = industyId;
    }

    public String getInstSubAddress() {
        return instSubAddress;
    }

    public void setInstSubAddress(String instSubAddress) {
        this.instSubAddress = instSubAddress == null ? null : instSubAddress.trim();
    }

    public String getInstSubWebsite() {
        return instSubWebsite;
    }

    public void setInstSubWebsite(String instSubWebsite) {
        this.instSubWebsite = instSubWebsite == null ? null : instSubWebsite.trim();
    }

    public String getInstSubWechat() {
        return instSubWechat;
    }

    public void setInstSubWechat(String instSubWechat) {
        this.instSubWechat = instSubWechat == null ? null : instSubWechat.trim();
    }

    public String getInstSubQq() {
        return instSubQq;
    }

    public void setInstSubQq(String instSubQq) {
        this.instSubQq = instSubQq == null ? null : instSubQq.trim();
    }

    public String getInstSubOthers() {
        return instSubOthers;
    }

    public void setInstSubOthers(String instSubOthers) {
        this.instSubOthers = instSubOthers == null ? null : instSubOthers.trim();
    }

    public Integer getInstSubRegisterDate() {
        return instSubRegisterDate;
    }

    public void setInstSubRegisterDate(Integer instSubRegisterDate) {
        this.instSubRegisterDate = instSubRegisterDate;
    }

    public String getInstSubIntroduction() {
        return instSubIntroduction;
    }

    public void setInstSubIntroduction(String instSubIntroduction) {
        this.instSubIntroduction = instSubIntroduction == null ? null : instSubIntroduction.trim();
    }

    public String getInstSubMemo() {
        return instSubMemo;
    }

    public void setInstSubMemo(String instSubMemo) {
        this.instSubMemo = instSubMemo == null ? null : instSubMemo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}