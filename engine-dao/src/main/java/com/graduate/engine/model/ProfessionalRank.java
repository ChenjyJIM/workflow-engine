package com.graduate.engine.model;

import lombok.Data;

@Data
public class ProfessionalRank {
    private Integer profId;

    private String profRankName;

    private String profRankGrade;

    private Boolean stop;
}