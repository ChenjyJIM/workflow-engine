package com.graduate.engine.model;

import lombok.Data;

@Data
public class Institute {
    private Integer instId;

    private String instName;

    private String instShort;

    private String instEngName;

    private Byte industryId;

    private String instAddress;

    private String instWebsite;

    private String instWechat;

    private String instQq;

    private String instOthers;

    private Integer instRegisterDate;

    private String instIntroduction;

    private String instMemo;

    private Boolean stop;
}