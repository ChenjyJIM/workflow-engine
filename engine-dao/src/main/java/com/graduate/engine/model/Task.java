package com.graduate.engine.model;

import lombok.Data;

@Data
public class Task {
    private Integer taskId;

    private Integer actSubId;

    private Integer actId;

    private String taskName;

    private String taskShort;

    private String taskEngName;

    private Integer taskDateFrom;

    private Integer taskDateTo;

    private Byte taskPriority;

    private Byte taskStatusId;

    private String taskMemo;

    private String taskRestartMemo;

    private Boolean stop;
}