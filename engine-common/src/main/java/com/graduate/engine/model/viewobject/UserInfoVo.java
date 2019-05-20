package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoVo {
    private Long id;

    private Long roleId;

    private String loginName;

    private String loginPassword;

    private Long personId;

    private Long instId;

    private String name;

    private String sex;

    private Long birthday;

    /**
     * 将时间转换为String类型，方便前端显示
     */
    private String formatBirthday;

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

    private Long personMemberId;

    private Long personMemberDate;

    private String memo;
}
