package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstPeriodComp {
    private Integer instPeriodCompId;

    private Integer instId;

    private Integer instPeriodId;

    private Byte instPeriodCompOrder;

    private Integer compId;

    private Integer compTitleId;

    private String instPeriodCompMemo;
}