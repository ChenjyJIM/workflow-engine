package com.graduate.engine.model;

public class Industry {
    private Integer industryId;

    private String indusName;

    private String indusShort;

    private Boolean stop;

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndusName() {
        return indusName;
    }

    public void setIndusName(String indusName) {
        this.indusName = indusName == null ? null : indusName.trim();
    }

    public String getIndusShort() {
        return indusShort;
    }

    public void setIndusShort(String indusShort) {
        this.indusShort = indusShort == null ? null : indusShort.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}