package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstSubPeriodComp {
    private Integer instSubPeriodCompId;

    private Integer instSubId;

    private Integer instSubPeriodId;

    private Byte instSubPeriodCompOrder;

    private Integer compId;

    private Integer compTitleId;

    private String instSubPeriodCompMemo;
}