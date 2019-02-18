package com.graduate.engine.model;

public class InstPeriod {
    private Integer instPeriodId;

    private Integer instId;

    private Byte instPeriodNo;

    private Integer instPeriodFrom;

    private Integer instPeriodTo;

    private String instMemo;

    public Integer getInstPeriodId() {
        return instPeriodId;
    }

    public void setInstPeriodId(Integer instPeriodId) {
        this.instPeriodId = instPeriodId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Byte getInstPeriodNo() {
        return instPeriodNo;
    }

    public void setInstPeriodNo(Byte instPeriodNo) {
        this.instPeriodNo = instPeriodNo;
    }

    public Integer getInstPeriodFrom() {
        return instPeriodFrom;
    }

    public void setInstPeriodFrom(Integer instPeriodFrom) {
        this.instPeriodFrom = instPeriodFrom;
    }

    public Integer getInstPeriodTo() {
        return instPeriodTo;
    }

    public void setInstPeriodTo(Integer instPeriodTo) {
        this.instPeriodTo = instPeriodTo;
    }

    public String getInstMemo() {
        return instMemo;
    }

    public void setInstMemo(String instMemo) {
        this.instMemo = instMemo == null ? null : instMemo.trim();
    }
}