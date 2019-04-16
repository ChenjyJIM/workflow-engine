package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstSubPeriodComp {
    private Long instSubPeriodCompId;

    private Long instSubId;

    private Long instSubPeriodId;

    private Integer instSubPeriodCompOrder;

    private Long compId;

    private Integer compTitleId;

    private String instSubPeriodCompMemo;
}
