package com.graduate.engine.model;

public class CompanyTitle {
    private Integer compTitleId;

    private String compTitleName;

    private Boolean stop;

    public Integer getCompTitleId() {
        return compTitleId;
    }

    public void setCompTitleId(Integer compTitleId) {
        this.compTitleId = compTitleId;
    }

    public String getCompTitleName() {
        return compTitleName;
    }

    public void setCompTitleName(String compTitleName) {
        this.compTitleName = compTitleName == null ? null : compTitleName.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}