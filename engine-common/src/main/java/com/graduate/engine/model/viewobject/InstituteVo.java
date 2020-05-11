package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;


/**
 * 学会前端返回对象VO
 *
 * @author lianglili
 */
@Data
public class InstituteVo {
    private Long instId;

    private String instName;

    private String instShort;

    private String instEngName;

    private Integer industryId;

    private String instAddress;

    private String instWebsite;

    private String instWechat;

    private String instQq;

    private String instOthers;

    private Long instRegisterDate;

    private String instIntroduction;

    private String instMemo;

    /**
     * 学会行业分类名称
     */
    private String industryName;

    private List<InstPeriodVo> instPeriods;

    /**
     * 学会会员总数
     */
    private Integer memberCount;

    /**
     * 学会单位会员总数
     */
    private Integer compCount;

    private String currentPeriod;

    private Boolean stop;
}

