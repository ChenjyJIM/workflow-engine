package com.graduate.engine.model;

import lombok.Data;

@Data
public class CompanyMember {
    private Long compId;

    private Long instId;

    private String compName;

    private String compShort;

    private String compEngName;

    private Byte compPropId;

    private String compAddress;

    private String compWebsite;

    private Long compRegisterDate;

    private Integer compRegisterCapital;

    private String compLegalName;

    private String compLegalInform;

    private Long personId;

    private Integer compEmployeeCount;

    private Double compEmployeeCollegeRate;

    private Integer compEmployeeItCount;

    private Double compEmployeeItRate;

    private String compBusiness;

    private String memo;

    private Long compMemberId;

    private Long compMemberDate;

    private Boolean stop;
}
