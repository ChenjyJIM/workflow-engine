package com.graduate.engine.model;

public class InstPeriodComp {
    private Integer instPeriodCompId;

    private Integer instId;

    private Integer instPeriodId;

    private Byte instPeriodCompOrder;

    private Integer compId;

    private Integer compTitleId;

    private String instPeriodCompMemo;

    public Integer getInstPeriodCompId() {
        return instPeriodCompId;
    }

    public void setInstPeriodCompId(Integer instPeriodCompId) {
        this.instPeriodCompId = instPeriodCompId;
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

    public Byte getInstPeriodCompOrder() {
        return instPeriodCompOrder;
    }

    public void setInstPeriodCompOrder(Byte instPeriodCompOrder) {
        this.instPeriodCompOrder = instPeriodCompOrder;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public Integer getCompTitleId() {
        return compTitleId;
    }

    public void setCompTitleId(Integer compTitleId) {
        this.compTitleId = compTitleId;
    }

    public String getInstPeriodCompMemo() {
        return instPeriodCompMemo;
    }

    public void setInstPeriodCompMemo(String instPeriodCompMemo) {
        this.instPeriodCompMemo = instPeriodCompMemo == null ? null : instPeriodCompMemo.trim();
    }
}