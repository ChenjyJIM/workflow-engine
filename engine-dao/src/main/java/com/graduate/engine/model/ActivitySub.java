package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActivitySub {
    private Integer actSubId;

    private Integer actId;

    private Integer instId;

    private Integer instSubId;

    private String actSubName;

    private String actSubShort;

    private String actSubEngName;

    private Byte industryId;

    private Integer actSubDateFrom;

    private Integer actSubDataTo;

    private String actSubAddress;

    private Byte actSubPriority;

    private Byte actSubStatusId;

    private String actSubMemo;

    private Integer actSubSplit;

    private Integer actSubMerge;

    private Integer actSubFatherId;

    private String actSubRestartMemo;

    private Boolean stop;
}