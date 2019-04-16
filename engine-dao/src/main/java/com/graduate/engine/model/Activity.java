package com.graduate.engine.model;

import lombok.Data;

@Data
public class Activity {
    private Long actId;

    private Long instId;

    private Long instSubId;

    private Long personId;

    private String actName;

    private String actShort;

    private String actEngName;

    private Integer industryId;

    private Long actDate;

    private Long actDateFrom;

    private Long actDateTo;

    private String actAddress;

    private Integer actPriority;

    private Integer actStatusId;

    private String actMemo;

    private Boolean stop;
}
