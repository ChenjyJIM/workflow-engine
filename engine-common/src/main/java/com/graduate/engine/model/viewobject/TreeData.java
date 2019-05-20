package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

@Data
public class TreeData  {

    private String name;
    private String priority;
    private String charger;
    private Long id;
    private Long parentId;
    private String startTime;
    private String endTime;
    private String introduction;
    private String type;
    private List<TreeData> children;
    private Boolean publish;

}
