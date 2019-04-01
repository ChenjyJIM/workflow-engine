package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstSubPeriodPerson {
    private Long instSubPeriodPersonId;

    private Long instSubId;

    private Long instSubPeriodId;

    private Byte instSubPeriodPersonOrder;

    private Long personId;

    private Integer personTitleId;

    private String instSubPeriodPersonMemo;
}
