package com.graduate.engine.model;

import lombok.Data;

@Data
public class TaskCheckPoint {
    private Long taskCheckPointId;

    private Long taskId;

    private String taskCheckPointName;

    private String taskCheckPointMemo;

    private Long taskCheckPointDate;
}
