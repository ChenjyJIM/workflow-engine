package com.graduate.engine.model;

public class InstSubPeriod {
    private Integer instSubPeriodId;

    private Integer instSubId;

    private Byte instSubPeriodNo;

    private Integer instSubPeriodFrom;

    private Integer instSubPeriodTo;

    private String instSubMemo;

    public Integer getInstSubPeriodId() {
        return instSubPeriodId;
    }

    public void setInstSubPeriodId(Integer instSubPeriodId) {
        this.instSubPeriodId = instSubPeriodId;
    }

    public Integer getInstSubId() {
        return instSubId;
    }

    public void setInstSubId(Integer instSubId) {
        this.instSubId = instSubId;
    }

    public Byte getInstSubPeriodNo() {
        return instSubPeriodNo;
    }

    public void setInstSubPeriodNo(Byte instSubPeriodNo) {
        this.instSubPeriodNo = instSubPeriodNo;
    }

    public Integer getInstSubPeriodFrom() {
        return instSubPeriodFrom;
    }

    public void setInstSubPeriodFrom(Integer instSubPeriodFrom) {
        this.instSubPeriodFrom = instSubPeriodFrom;
    }

    public Integer getInstSubPeriodTo() {
        return instSubPeriodTo;
    }

    public void setInstSubPeriodTo(Integer instSubPeriodTo) {
        this.instSubPeriodTo = instSubPeriodTo;
    }

    public String getInstSubMemo() {
        return instSubMemo;
    }

    public void setInstSubMemo(String instSubMemo) {
        this.instSubMemo = instSubMemo == null ? null : instSubMemo.trim();
    }
}