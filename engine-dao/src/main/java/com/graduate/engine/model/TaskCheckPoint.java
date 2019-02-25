package com.graduate.engine.model;

import lombok.Data;

@Data
public class TaskCheckPoint {
    private Integer taskCheckPointId;

    private Integer taskId;

    private String taskCheckPointName;

    private String taskCheckPointMemo;

    private Integer taskCheckPointDate;
}