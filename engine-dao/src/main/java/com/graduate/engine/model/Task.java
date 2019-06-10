package com.graduate.engine.model;

import lombok.Data;

@Data
public class Task {
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
