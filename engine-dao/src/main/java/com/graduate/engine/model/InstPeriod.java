package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstPeriod {
    private Integer instPeriodId;

    private Integer instId;

    private Byte instPeriodNo;

    private Integer instPeriodFrom;

    private Integer instPeriodTo;

    private String instMemo;
}