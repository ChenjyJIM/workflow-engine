package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActivityStatus {
    private Long actStatusId;

    private String actStatusName;

    private Byte actStatusOrder;

    private Boolean actStatusSpecial;

    private Boolean stop;
}
