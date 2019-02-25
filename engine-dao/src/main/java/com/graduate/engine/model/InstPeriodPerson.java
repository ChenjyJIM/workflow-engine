package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstPeriodPerson {
    private Integer instPeriodPersonId;

    private Integer instId;

    private Integer instPeriodId;

    private Byte instPeriodPersonOrder;

    private Integer personId;

    private Integer personTitleId;

    private String instPeriodPersonMemo;
}