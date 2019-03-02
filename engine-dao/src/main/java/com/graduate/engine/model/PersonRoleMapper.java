package com.graduate.engine.model;

public class PersonRoleMapper {
    private Integer personRoleId;

    private Integer personId;

    private Integer roleId;

    private String memo;

    private Boolean stop;

    public Integer getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(Integer personRoleId) {
        this.personRoleId = personRoleId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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