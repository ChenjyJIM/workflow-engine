package com.graduate.engine.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息修改请求类
 *
 * @author lianglili
 */
@Getter
@Setter
public class MemberModifiedRequest {
    /**
     * 会员所需信息
     */
    private Long personId;
    private String name;
    private String sex;
    private String birthday;
    private String ethnic;
    private String partisan;
    private String mail;
    private String phone1;
    private String qq;
    private String wechat;
    private String memo;

    /**
     * 访客所需信息
     */
    private Long guestId;
    private String guestName;
    private String guestSex;
    private String guestEmail;
    private String guestPhone;
}
