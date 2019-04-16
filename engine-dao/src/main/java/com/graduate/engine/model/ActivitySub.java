package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActivitySub {
    private Long actSubId;

    private Long actId;

    private Long instId;

    private Long instSubId;

    private String actSubName;

    private String actSubShort;

    private String actSubEngName;

    private Integer industryId;

    private Long actSubDateFrom;

    private Long actSubDataTo;

    private String actSubAddress;

    private Integer actSubPriority;

    private Integer actSubStatusId;

    private String actSubMemo;

    private Long actSubSplit;

    private Long actSubMerge;

    private Long actSubFatherId;

    private String actSubRestartMemo;

    private Boolean stop;
}
