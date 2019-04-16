package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstPeriodComp {
    private Long instPeriodCompId;

    private Long instId;

    private Long instPeriodId;

    private Integer instPeriodCompOrder;

    private Long compId;

    private Integer compTitleId;

    private String instPeriodCompMemo;
}
