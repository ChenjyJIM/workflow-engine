package com.graduate.engine.model;

import lombok.Data;

@Data
public class PersonMember {
    private Integer personId;

    private Integer instId;

    private String name;

    private String sex;

    private Integer birthday;

    private String ethnic;

    private String partisan;

    private String mail;

    private String phone1;

    private String phone2;

    private String qq;

    private String wechat;

    private String others;

    private String personPublicDuty;

    private String personAwards;

    private Integer educationId;

    private Integer personMemberId;

    private Integer personMemberDate;

    private String memo;

    private Boolean stop;
}