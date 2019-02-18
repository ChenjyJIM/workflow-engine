package com.graduate.engine.model;

public class PersonResume {
    private Integer personResumeId;

    private Integer instId;

    private Integer personId;

    private String personResumeComp;

    private String personResumeAddr;

    private String personResumeDept;

    private String personResumePost;

    private Integer personResumeRank;

    private String personResumeSpec;

    private Integer personResumeFrom;

    private Integer personResumeTo;

    private String memo;

    public Integer getPersonResumeId() {
        return personResumeId;
    }

    public void setPersonResumeId(Integer personResumeId) {
        this.personResumeId = personResumeId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonResumeComp() {
        return personResumeComp;
    }

    public void setPersonResumeComp(String personResumeComp) {
        this.personResumeComp = personResumeComp == null ? null : personResumeComp.trim();
    }

    public String getPersonResumeAddr() {
        return personResumeAddr;
    }

    public void setPersonResumeAddr(String personResumeAddr) {
        this.personResumeAddr = personResumeAddr == null ? null : personResumeAddr.trim();
    }

    public String getPersonResumeDept() {
        return personResumeDept;
    }

    public void setPersonResumeDept(String personResumeDept) {
        this.personResumeDept = personResumeDept == null ? null : personResumeDept.trim();
    }

    public String getPersonResumePost() {
        return personResumePost;
    }

    public void setPersonResumePost(String personResumePost) {
        this.personResumePost = personResumePost == null ? null : personResumePost.trim();
    }

    public Integer getPersonResumeRank() {
        return personResumeRank;
    }

    public void setPersonResumeRank(Integer personResumeRank) {
        this.personResumeRank = personResumeRank;
    }

    public String getPersonResumeSpec() {
        return personResumeSpec;
    }

    public void setPersonResumeSpec(String personResumeSpec) {
        this.personResumeSpec = personResumeSpec == null ? null : personResumeSpec.trim();
    }

    public Integer getPersonResumeFrom() {
        return personResumeFrom;
    }

    public void setPersonResumeFrom(Integer personResumeFrom) {
        this.personResumeFrom = personResumeFrom;
    }

    public Integer getPersonResumeTo() {
        return personResumeTo;
    }

    public void setPersonResumeTo(Integer personResumeTo) {
        this.personResumeTo = personResumeTo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}