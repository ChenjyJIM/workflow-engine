package com.graduate.engine.model;

public class InstPeriodPerson {
    private Integer instPeriodPersonId;

    private Integer instId;

    private Integer instPeriodId;

    private Byte instPeriodPersonOrder;

    private Integer personId;

    private Integer personTitleId;

    private String instPeriodPersonMemo;

    public Integer getInstPeriodPersonId() {
        return instPeriodPersonId;
    }

    public void setInstPeriodPersonId(Integer instPeriodPersonId) {
        this.instPeriodPersonId = instPeriodPersonId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getInstPeriodId() {
        return instPeriodId;
    }

    public void setInstPeriodId(Integer instPeriodId) {
        this.instPeriodId = instPeriodId;
    }

    public Byte getInstPeriodPersonOrder() {
        return instPeriodPersonOrder;
    }

    public void setInstPeriodPersonOrder(Byte instPeriodPersonOrder) {
        this.instPeriodPersonOrder = instPeriodPersonOrder;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonTitleId() {
        return personTitleId;
    }

    public void setPersonTitleId(Integer personTitleId) {
        this.personTitleId = personTitleId;
    }

    public String getInstPeriodPersonMemo() {
        return instPeriodPersonMemo;
    }

    public void setInstPeriodPersonMemo(String instPeriodPersonMemo) {
        this.instPeriodPersonMemo = instPeriodPersonMemo == null ? null : instPeriodPersonMemo.trim();
    }
}