package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

/**
 * 学会信息页面返回三个信息List
 */
@Data
public class InfoListVo {

    private List<SelectList> subList;

    private List<SelectList> memberList;

    private List<SelectList> compList;


}
