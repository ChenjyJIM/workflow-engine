package com.graduate.engine.model.viewobject;

import lombok.Data;

/**
 * 简单的分会信息
 */
@Data
public class InstSubsVo {
    private Long instSubId;

    private Long instId;

    private String instSubName;

    private String instSubShort;

    private String instSubEngName;

    private Integer industryId;

    private String instSubAddress;

    private String instSubWebsite;

    private String instSubWechat;

    private String instSubQq;

    private String instSubOthers;

    private String instSubRegisterDate;

    private String instSubIntroduction;

    private String instSubMemo;
    /**
     * 行业分类名称
     */
    private String industryName;

    /**
     * 分会负责人名字
     */
    private String chargerName;

    /**
     * 分会负责人职称
     */
    private String personTitleName;

    /**
     * 分会当前第几届
     */
    private Integer instSubPeriodNo;

    private String instSubPeriodFrom;

    private String instSubPeriodTo;

    //todo 还需要增加企业理事单位
}
