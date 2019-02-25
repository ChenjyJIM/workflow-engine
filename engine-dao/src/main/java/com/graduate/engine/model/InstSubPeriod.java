package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstSubPeriod {
    private Integer instSubPeriodId;

    private Integer instSubId;

    private Byte instSubPeriodNo;

    private Integer instSubPeriodFrom;

    private Integer instSubPeriodTo;

    private String instSubMemo;
}