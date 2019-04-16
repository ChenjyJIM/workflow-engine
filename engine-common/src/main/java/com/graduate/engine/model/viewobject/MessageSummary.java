package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 信息简介，没有信息内容
 */
@Getter
@Setter
public class MessageSummary {
    private Long messageId;

    private String messageTitle;

    private Long createUserId;

    private String createUserName;

    private Date createTime;
}
