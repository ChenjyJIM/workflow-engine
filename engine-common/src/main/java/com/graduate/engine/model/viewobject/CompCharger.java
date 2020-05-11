package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 学会或分会 企业理事（负责人）信息
 *
 * @author lianglili
 */
@Data
public class CompCharger {
    private Long compId;

    private Long instId;

    private String compName;

    private String compShort;

    private String compEngName;

    private Integer compPropId;

    private String compAddress;

    private String compWebsite;

    private Long compRegisterDate;

    private Integer compRegisterCapital;

    private String compLegalName;

    private String compLegalInform;

    private Long personId;

    private Integer compEmployeeCount;

    private BigDecimal compEmployeeCollegeRate;

    private Integer compEmployeeItCount;

    private BigDecimal compEmployeeItRate;

    private String compBusiness;

    private String memo;

    private Long compMemberId;

    private Long compMemberDate;

    private Boolean stop;
}
