package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstSubPeriodPerson {
    private Integer instSubPeriodPersonId;

    private Integer instSubId;

    private Integer instSubPeriodId;

    private Byte instSubPeriodPersonOrder;

    private Integer personId;

    private Integer personTitleId;

    private String instSubPeriodPersonMemo;
}