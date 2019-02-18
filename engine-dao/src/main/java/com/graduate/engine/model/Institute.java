package com.graduate.engine.model;

public class Institute {
    private Integer instId;

    private String instName;

    private String instShort;

    private String instEngName;

    private Byte industyId;

    private String instAddress;

    private String instWebsite;

    private String instWechat;

    private String instQq;

    private String instOthers;

    private Integer instRegisterDate;

    private String instIntroduction;

    private String instMemo;

    private Boolean stop;

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName == null ? null : instName.trim();
    }

    public String getInstShort() {
        return instShort;
    }

    public void setInstShort(String instShort) {
        this.instShort = instShort == null ? null : instShort.trim();
    }

    public String getInstEngName() {
        return instEngName;
    }

    public void setInstEngName(String instEngName) {
        this.instEngName = instEngName == null ? null : instEngName.trim();
    }

    public Byte getIndustyId() {
        return industyId;
    }

    public void setIndustyId(Byte industyId) {
        this.industyId = industyId;
    }

    public String getInstAddress() {
        return instAddress;
    }

    public void setInstAddress(String instAddress) {
        this.instAddress = instAddress == null ? null : instAddress.trim();
    }

    public String getInstWebsite() {
        return instWebsite;
    }

    public void setInstWebsite(String instWebsite) {
        this.instWebsite = instWebsite == null ? null : instWebsite.trim();
    }

    public String getInstWechat() {
        return instWechat;
    }

    public void setInstWechat(String instWechat) {
        this.instWechat = instWechat == null ? null : instWechat.trim();
    }

    public String getInstQq() {
        return instQq;
    }

    public void setInstQq(String instQq) {
        this.instQq = instQq == null ? null : instQq.trim();
    }

    public String getInstOthers() {
        return instOthers;
    }

    public void setInstOthers(String instOthers) {
        this.instOthers = instOthers == null ? null : instOthers.trim();
    }

    public Integer getInstRegisterDate() {
        return instRegisterDate;
    }

    public void setInstRegisterDate(Integer instRegisterDate) {
        this.instRegisterDate = instRegisterDate;
    }

    public String getInstIntroduction() {
        return instIntroduction;
    }

    public void setInstIntroduction(String instIntroduction) {
        this.instIntroduction = instIntroduction == null ? null : instIntroduction.trim();
    }

    public String getInstMemo() {
        return instMemo;
    }

    public void setInstMemo(String instMemo) {
        this.instMemo = instMemo == null ? null : instMemo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}