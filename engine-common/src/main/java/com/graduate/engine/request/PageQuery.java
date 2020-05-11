package com.graduate.engine.request;

import java.io.Serializable;

/**
 * @author lianglili
 */
public class PageQuery implements Serializable {
    private Integer page = 1; // 页码
    private Integer size = 10;// 页大小
    @SuppressWarnings("unused")
    private long start = 0;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public long getStart() {
        return page > 0 ? (page - 1) * size : page * size;
    }

    public void setStart(long start) {
        this.start = start;
    }
}
