package com.graduate.engine.model;

import lombok.Data;

@Data
public class InstituteSub {
    private Integer instSubId;

    private String instSubName;

    private String instSubShort;

    private String instSubEngName;

    private Byte industryId;

    private String instSubAddress;

    private String instSubWebsite;

    private String instSubWechat;

    private String instSubQq;

    private String instSubOthers;

    private Integer instSubRegisterDate;

    private String instSubIntroduction;

    private String instSubMemo;

    private Boolean stop;
}