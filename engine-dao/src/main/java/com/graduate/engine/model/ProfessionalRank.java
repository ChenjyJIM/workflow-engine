package com.graduate.engine.model;

public class ProfessionalRank {
    private Integer profId;

    private String profRankName;

    private String profRankGrade;

    private Boolean stop;

    public Integer getProfId() {
        return profId;
    }

    public void setProfId(Integer profId) {
        this.profId = profId;
    }

    public String getProfRankName() {
        return profRankName;
    }

    public void setProfRankName(String profRankName) {
        this.profRankName = profRankName == null ? null : profRankName.trim();
    }

    public String getProfRankGrade() {
        return profRankGrade;
    }

    public void setProfRankGrade(String profRankGrade) {
        this.profRankGrade = profRankGrade == null ? null : profRankGrade.trim();
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}