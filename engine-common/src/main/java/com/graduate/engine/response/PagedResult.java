package com.graduate.engine.response;

import java.util.List;

/**
 * 分页查询结果
 */
public class PagedResult<T> {
    /**
     * 当前页码，从1开始
     */
    private int page = 1;
    /**
     * 页大小
     */
    private int size = 10;
    private List<T> items;
    /**
     * 总数
     */
    private Long total;
    /**
     * 页数
     */
    private Integer pages;
    private String ext; // 扩展
    private boolean nextPage;// 上一页
    private boolean prevPage;// 下一页

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        calc();
        this.size = size;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setTotal(Long total) {
        this.total = total;
        calc();
    }

    public Long getTotal() {
        return total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void calc() {
        try {
            if (total != null && size != 0) {
                Double div = new Double(total) / size;
                boolean gt = div > total / size;
                pages = gt ? div.intValue() + 1 : div.intValue();
                pages = pages <= 0 ? 1 : pages;
                if (page >= pages) {
                    if (page == 1) {
                        prevPage = false;
                    } else {
                        prevPage = true;
                    }
                    nextPage = false;
                } else {
                    if (page == 1) {
                        prevPage = false;
                    } else {
                        prevPage = true;
                    }
                    nextPage = true;
                }
            }
        } catch (Exception e) {
        }
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }
}
