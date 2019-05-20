package com.graduate.engine.request;

import lombok.Data;

@Data
public class TaskCheckPointRequest {

    private Long taskCheckPointId;

    private Long taskId;

    private String taskCheckPointName;

    private String taskCheckPointMemo;

    private String taskCheckPointDate;
}
