package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

@Data
public class InstituteSubVo {
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

    private List<InstSubPeriodVo> instPeriods;

    private Boolean stop;
}
