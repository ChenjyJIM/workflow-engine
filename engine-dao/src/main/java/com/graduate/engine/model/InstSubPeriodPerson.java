package com.graduate.engine.model;

public class InstSubPeriodPerson {
    private Integer instSubPeriodPersonId;

    private Integer instSubId;

    private Integer instSubPeriodId;

    private Byte instSubPeriodPersonOrder;

    private Integer personId;

    private Integer personTitleId;

    private String instSubPeriodPersonMemo;

    public Integer getInstSubPeriodPersonId() {
        return instSubPeriodPersonId;
    }

    public void setInstSubPeriodPersonId(Integer instSubPeriodPersonId) {
        this.instSubPeriodPersonId = instSubPeriodPersonId;
    }

    public Integer getInstSubId() {
        return instSubId;
    }

    public void setInstSubId(Integer instSubId) {
        this.instSubId = instSubId;
    }

    public Integer getInstSubPeriodId() {
        return instSubPeriodId;
    }

    public void setInstSubPeriodId(Integer instSubPeriodId) {
        this.instSubPeriodId = instSubPeriodId;
    }

    public Byte getInstSubPeriodPersonOrder() {
        return instSubPeriodPersonOrder;
    }

    public void setInstSubPeriodPersonOrder(Byte instSubPeriodPersonOrder) {
        this.instSubPeriodPersonOrder = instSubPeriodPersonOrder;
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

    public String getInstSubPeriodPersonMemo() {
        return instSubPeriodPersonMemo;
    }

    public void setInstSubPeriodPersonMemo(String instSubPeriodPersonMemo) {
        this.instSubPeriodPersonMemo = instSubPeriodPersonMemo == null ? null : instSubPeriodPersonMemo.trim();
    }
}