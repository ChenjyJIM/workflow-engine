package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyTaskVo implements Serializable {

    private Long taskId;

    private Long actSubId;

    private Long actId;

    private String taskName;

    private String taskShort;

    private String taskEngName;

    private Long taskDateFrom;

    private Long taskDateTo;

    private Integer taskPriority;

    private Long taskStatusId;

    private String taskMemo;

    private String taskRestartMemo;

    private Boolean stop;

}
