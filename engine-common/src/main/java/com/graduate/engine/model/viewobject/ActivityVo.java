package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityVo {
    private Long actId;

    private Long instId;

    private Long instSubId;

    private Long personId;

    private String actName;

    private String actShort;

    private String actEngName;

    private Integer industryId;

    private String actAddress;

    private Integer actPriority;

    private Long actStatusId;

    private String actMemo;

    private Boolean publish;

    private Boolean stop;

    private String instName;

    private String instSubName;

    private String personName;

    private String industryName;

    private String actDate;

    private String actDateFrom;

    private String actDateTo;

    private String actStatusName;

}
