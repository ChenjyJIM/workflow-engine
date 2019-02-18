package com.graduate.engine.model;

public class Union {
    private Integer unionId;

    private String unionName;

    private String unionShort;

    private String unionEngName;

    private String unionAddress;

    private String unionWebsite;

    private String unionWechat;

    private String unionQq;

    private String unionOthers;

    private Integer unionRegisterDate;

    private String unionIntroduction;

    private String unionMemo;

    private Boolean stop;

    public Integer getUnionId() {
        return unionId;
    }

    public void setUnionId(Integer unionId) {
        this.unionId = unionId;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName == null ? null : unionName.trim();
    }

    public String getUnionShort() {
        return unionShort;
    }

    public void setUnionShort(String unionShort) {
        this.unionShort = unionShort == null ? null : unionShort.trim();
    }

    public String getUnionEngName() {
        return unionEngName;
    }

    public void setUnionEngName(String unionEngName) {
        this.unionEngName = unionEngName == null ? null : unionEngName.trim();
    }

    public String getUnionAddress() {
        return unionAddress;
    }

    public void setUnionAddress(String unionAddress) {
        this.unionAddress = unionAddress == null ? null : unionAddress.trim();
    }

    public String getUnionWebsite() {
        return unionWebsite;
    }

    public void setUnionWebsite(String unionWebsite) {
        this.unionWebsite = unionWebsite == null ? null : unionWebsite.trim();
    }

    public String getUnionWechat() {
        return unionWechat;
    }

    public void setUnionWechat(String unionWechat) {
        this.unionWechat = unionWechat == null ? null : unionWechat.trim();
    }

    public String getUnionQq() {
        return unionQq;
    }

    public void setUnionQq(String unionQq) {
        this.unionQq = unionQq == null ? null : unionQq.trim();
    }

    public String getUnionOthers() {
        return unionOthers;
    }

    public void setUnionOthers(String unionOthers) {
        this.unionOthers = unionOthers == null ? null : unionOthers.trim();
    }

    public Integer getUnionRegisterDate() {
        return unionRegisterDate;
    }

    public void setUnionRegisterDate(Integer unionRegisterDate) {
        this.unionRegisterDate = unionRegisterDate;
    }

    public String getUnionIntroduction() {
        return unionIntroduction;
    }

    public void setUnionIntroduction(String unionIntroduction) {
        this.unionIntroduction = unionIntroduction == null ? null : unionIntroduction.trim();
    }

    public String getUnionMemo() {
        return unionMemo;
    }

    public void setUnionMemo(String unionMemo) {
        this.unionMemo = unionMemo == null ? null : unionMemo.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}