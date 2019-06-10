package com.graduate.engine.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author jimmy
 */
@Getter
@Setter
public class RegisterRequest implements Serializable {
    private String loginName;
    private String loginPassword;
    private Long personId;
    private Integer instId;
    private String name;
    private String sex;
    private String birthday;
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
    /**
     * 会员id
     */
    private Long personMemberId;
    private Long personMemberDate;
    private String memo;
    /**
     * 1表示会员注册 2表示访客注册
     */
    private Integer type;
}
