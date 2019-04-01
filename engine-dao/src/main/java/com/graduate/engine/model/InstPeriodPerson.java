package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstPeriodPerson {
    private Long instPeriodPersonId;

    private Long instId;

    private Long instPeriodId;

    private Byte instPeriodPersonOrder;

    private Long personId;

    private Integer personTitleId;

    private String instPeriodPersonMemo;
}
