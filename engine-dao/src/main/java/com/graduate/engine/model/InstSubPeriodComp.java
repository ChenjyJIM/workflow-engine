package com.graduate.engine.model;

public class InstSubPeriodComp {
    private Integer instSubPeriodCompId;

    private Integer instSubId;

    private Integer instSubPeriodId;

    private Byte instSubPeriodCompOrder;

    private Integer compId;

    private Integer compTitleId;

    private String instSubPeriodCompMemo;

    public Integer getInstSubPeriodCompId() {
        return instSubPeriodCompId;
    }

    public void setInstSubPeriodCompId(Integer instSubPeriodCompId) {
        this.instSubPeriodCompId = instSubPeriodCompId;
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

    public Byte getInstSubPeriodCompOrder() {
        return instSubPeriodCompOrder;
    }

    public void setInstSubPeriodCompOrder(Byte instSubPeriodCompOrder) {
        this.instSubPeriodCompOrder = instSubPeriodCompOrder;
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

    public String getInstSubPeriodCompMemo() {
        return instSubPeriodCompMemo;
    }

    public void setInstSubPeriodCompMemo(String instSubPeriodCompMemo) {
        this.instSubPeriodCompMemo = instSubPeriodCompMemo == null ? null : instSubPeriodCompMemo.trim();
    }
}