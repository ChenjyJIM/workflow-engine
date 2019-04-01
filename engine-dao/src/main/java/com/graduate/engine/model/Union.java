package com.graduate.engine.model;

import lombok.Data;

@Data
public class Union {
    private Long unionId;

    private String unionName;

    private String unionShort;

    private String unionEngName;

    private String unionAddress;

    private String unionWebsite;

    private String unionWechat;

    private String unionQq;

    private String unionOthers;

    private Long unionRegisterDate;

    private String unionIntroduction;

    private String unionMemo;

    private Boolean stop;

}
