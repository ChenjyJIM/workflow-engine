package com.graduate.engine.model;

public class PersonMember {
    private Integer personId;

    private Integer instId;

    private String name;

    private String sex;

    private Integer birthday;

    private String ethnic;

    private String partisan;

    private String mail;

    private String phone1;

    private String phone2;

    private String qq;

    private String wechat;

    private String others;

    private String personPublicDuty;

    private String personAwards;

    private Integer educationId;

    private Integer personMemberId;

    private Integer personMemberDate;

    private String memo;

    private Boolean stop;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic == null ? null : ethnic.trim();
    }

    public String getPartisan() {
        return partisan;
    }

    public void setPartisan(String partisan) {
        this.partisan = partisan == null ? null : partisan.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1 == null ? null : phone1.trim();
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2 == null ? null : phone2.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others == null ? null : others.trim();
    }

    public String getPersonPublicDuty() {
        return personPublicDuty;
    }

    public void setPersonPublicDuty(String personPublicDuty) {
        this.personPublicDuty = personPublicDuty == null ? null : personPublicDuty.trim();
    }

    public String getPersonAwards() {
        return personAwards;
    }

    public void setPersonAwards(String personAwards) {
        this.personAwards = personAwards == null ? null : personAwards.trim();
    }

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public Integer getPersonMemberId() {
        return personMemberId;
    }

    public void setPersonMemberId(Integer personMemberId) {
        this.personMemberId = personMemberId;
    }

    public Integer getPersonMemberDate() {
        return personMemberDate;
    }

    public void setPersonMemberDate(Integer personMemberDate) {
        this.personMemberDate = personMemberDate;
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