package com.graduate.engine.model;

import lombok.Data;

@Data
public class CompanyMember {
    private Integer compId;

    private Integer instId;

    private String compName;

    private String compShort;

    private String compEngName;

    private Byte compPropId;

    private String compAddress;

    private String compWebsite;

    private Integer compRegisterDate;

    private Integer compRegisterCapital;

    private String compLegalName;

    private String compLegalInform;

    private Integer personId;

    private Integer compEmployeeCount;

    private Double compEmployeeCollegeRate;

    private Integer compEmployeeItCount;

    private Double compEmployeeItRate;

    private String compBusiness;

    private String memo;

    private Integer compMemberId;

    private Integer compMemberDate;

    private Boolean stop;
}