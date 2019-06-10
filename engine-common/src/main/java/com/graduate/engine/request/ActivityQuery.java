package com.graduate.engine.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ActivityQuery extends PageQuery implements Serializable {

    private String actName;
    private Boolean publish;
    private Long actStatusId;
    private Boolean sortByPriority;
    private Long personId;
    private Boolean stop;
    /**
     * 前端返回学会id和分会id
     * 格式：['1','2'] 第一个为学会id，第二个为分会id
     */
    private List<String> ids;

    private Long instId;

    private Long instSubId;

}
