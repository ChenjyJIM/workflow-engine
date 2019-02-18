package com.graduate.engine.model;

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

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName == null ? null : compName.trim();
    }

    public String getCompShort() {
        return compShort;
    }

    public void setCompShort(String compShort) {
        this.compShort = compShort == null ? null : compShort.trim();
    }

    public String getCompEngName() {
        return compEngName;
    }

    public void setCompEngName(String compEngName) {
        this.compEngName = compEngName == null ? null : compEngName.trim();
    }

    public Byte getCompPropId() {
        return compPropId;
    }

    public void setCompPropId(Byte compPropId) {
        this.compPropId = compPropId;
    }

    public String getCompAddress() {
        return compAddress;
    }

    public void setCompAddress(String compAddress) {
        this.compAddress = compAddress == null ? null : compAddress.trim();
    }

    public String getCompWebsite() {
        return compWebsite;
    }

    public void setCompWebsite(String compWebsite) {
        this.compWebsite = compWebsite == null ? null : compWebsite.trim();
    }

    public Integer getCompRegisterDate() {
        return compRegisterDate;
    }

    public void setCompRegisterDate(Integer compRegisterDate) {
        this.compRegisterDate = compRegisterDate;
    }

    public Integer getCompRegisterCapital() {
        return compRegisterCapital;
    }

    public void setCompRegisterCapital(Integer compRegisterCapital) {
        this.compRegisterCapital = compRegisterCapital;
    }

    public String getCompLegalName() {
        return compLegalName;
    }

    public void setCompLegalName(String compLegalName) {
        this.compLegalName = compLegalName == null ? null : compLegalName.trim();
    }

    public String getCompLegalInform() {
        return compLegalInform;
    }

    public void setCompLegalInform(String compLegalInform) {
        this.compLegalInform = compLegalInform == null ? null : compLegalInform.trim();
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getCompEmployeeCount() {
        return compEmployeeCount;
    }

    public void setCompEmployeeCount(Integer compEmployeeCount) {
        this.compEmployeeCount = compEmployeeCount;
    }

    public Double getCompEmployeeCollegeRate() {
        return compEmployeeCollegeRate;
    }

    public void setCompEmployeeCollegeRate(Double compEmployeeCollegeRate) {
        this.compEmployeeCollegeRate = compEmployeeCollegeRate;
    }

    public Integer getCompEmployeeItCount() {
        return compEmployeeItCount;
    }

    public void setCompEmployeeItCount(Integer compEmployeeItCount) {
        this.compEmployeeItCount = compEmployeeItCount;
    }

    public Double getCompEmployeeItRate() {
        return compEmployeeItRate;
    }

    public void setCompEmployeeItRate(Double compEmployeeItRate) {
        this.compEmployeeItRate = compEmployeeItRate;
    }

    public String getCompBusiness() {
        return compBusiness;
    }

    public void setCompBusiness(String compBusiness) {
        this.compBusiness = compBusiness == null ? null : compBusiness.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getCompMemberId() {
        return compMemberId;
    }

    public void setCompMemberId(Integer compMemberId) {
        this.compMemberId = compMemberId;
    }

    public Integer getCompMemberDate() {
        return compMemberDate;
    }

    public void setCompMemberDate(Integer compMemberDate) {
        this.compMemberDate = compMemberDate;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}