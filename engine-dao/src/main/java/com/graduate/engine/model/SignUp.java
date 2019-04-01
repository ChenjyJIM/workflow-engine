package com.graduate.engine.model;

import lombok.Data;

@Data
public class SignUp {
    private Integer signId;

    private String signName;

    private String signShort;

    private String signEngName;

    private Integer industryId;

    private Long actId;

    private Long signDateFrom;

    private Long signDateTo;

    private String memo;

    private Boolean stop;

}
