package com.graduate.engine.model;

import lombok.Data;

@Data
public class Activity {
    private Integer actId;

    private Integer instId;

    private Integer instSubId;

    private Integer personId;

    private String actName;

    private String actShort;

    private String actEngName;

    private Byte industryId;

    private Integer actDate;

    private Integer actDateFrom;

    private Integer actDateTo;

    private String actAddress;

    private Byte actPriority;

    private Byte actStatusId;

    private String actMemo;

    private Boolean stop;
}