package com.graduate.engine.model.dataobject;

import lombok.Data;

/**
 * @author lianglili
 */
@Data
public class PersonMember {
    /**
     * 主键
     */
    private Long personId;
    private Long instId;
    private String name;
    private String sex;
    private Long birthday;
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
    private Long educationId;
    /**
     * 会员id
     */
    private Long personMemberId;
    private Long personMemberDate;
    private String memo;
    private Short stop;
}
