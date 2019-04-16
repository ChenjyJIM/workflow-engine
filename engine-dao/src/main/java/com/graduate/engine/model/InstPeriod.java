package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstPeriod {
    private Long instPeriodId;

    private Long instId;

    private Integer instPeriodNo;

    private Long instPeriodFrom;

    private Long instPeriodTo;

    private String instMemo;
}
