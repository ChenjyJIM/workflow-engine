package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstituteSub {
    private Long instSubId;

    private Long instId;

    private String instSubName;

    private String instSubShort;

    private String instSubEngName;

    private Integer industryId;

    private String instSubAddress;

    private String instSubWebsite;

    private String instSubWechat;

    private String instSubQq;

    private String instSubOthers;

    private Long instSubRegisterDate;

    private String instSubIntroduction;

    private String instSubMemo;

    private Boolean stop;
}
