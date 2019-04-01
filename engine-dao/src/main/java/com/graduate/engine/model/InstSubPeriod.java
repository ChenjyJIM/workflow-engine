package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstSubPeriod {
    private Long instSubPeriodId;

    private Long instSubId;

    private Byte instSubPeriodNo;

    private Long instSubPeriodFrom;

    private Long instSubPeriodTo;

    private String instSubMemo;
}
