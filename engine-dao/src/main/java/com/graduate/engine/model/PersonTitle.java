package com.graduate.engine.model;

public class PersonTitle {
    private Integer personTitleId;

    private String personTitleName;

    private Boolean stop;

    public Integer getPersonTitleId() {
        return personTitleId;
    }

    public void setPersonTitleId(Integer personTitleId) {
        this.personTitleId = personTitleId;
    }

    public String getPersonTitleName() {
        return personTitleName;
    }

    public void setPersonTitleName(String personTitleName) {
        this.personTitleName = personTitleName == null ? null : personTitleName.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}