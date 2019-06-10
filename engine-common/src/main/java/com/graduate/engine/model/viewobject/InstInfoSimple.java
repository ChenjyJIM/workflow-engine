package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

/**
 * 简单学会信息
 *
 * @author jimmy
 */
@Data
public class InstInfoSimple {
    private String instName;
    private Long instId;
    private List<InstSubInfoSimple> subInfoSimpleList;

}
