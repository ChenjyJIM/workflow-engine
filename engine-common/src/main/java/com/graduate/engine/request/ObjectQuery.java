package com.graduate.engine.request;

import lombok.Data;

@Data
public class ObjectQuery extends PageQuery {

    /**
     * 用来查询的物品名字
     */
    private String name;

    /**
     * 根据垃圾种类查询
     */
    private Long garbageType;
}
