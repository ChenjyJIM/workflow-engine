package com.graduate.engine.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 学会查询条件
 */
@Getter
@Setter
public class InstituteQuery extends PageQuery {
    private String instName;
}
