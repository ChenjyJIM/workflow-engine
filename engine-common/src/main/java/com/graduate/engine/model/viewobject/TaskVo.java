package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

@Data
public class TaskVo {
    private Long taskId;

    private Long actSubId;

    private Long actId;

    private String taskName;

    private String taskShort;

    private String taskEngName;

    private String taskDateFrom;

    private String taskDateTo;

    private Integer taskPriority;

    private Long taskStatusId;

    private String taskMemo;

    private String taskRestartMemo;

    private Boolean stop;

    private Long personId;

    private String personName;

    private String taskStatusName;

    private String duty;

    private String priorityName;

    private String actName;

    private Boolean editable;

    private List<TaskCheckPointVo> taskCheckPointVoList;
}
