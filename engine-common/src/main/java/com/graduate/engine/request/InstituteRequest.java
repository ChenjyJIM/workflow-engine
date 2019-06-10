package com.graduate.engine.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端修改学会信息request
 * @author jimmy
 */
@Data
public class InstituteRequest implements Serializable {

    private Long instId;

    private String instName;

    private String instShort;

    private String instEngName;

    private String indusShort;

    private String instAddress;

    private String instWebsite;

    private String instWechat;

    private String instQq;

    private String instOthers;

    private String instIntroduction;

    private String instMemo;

    private Integer industryId;

    private Long personId;

    private String instPeriodTo;

    private String instPeriodMemo;

}
