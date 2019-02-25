package com.graduate.engine.model;

import lombok.Data;

@Data
public class TaskExec {
    private Integer taskExecId;

    private Integer taskId;

    private String taskExecName;

    private Byte taskExecStatus;

    private String taskExecReport;

    private String taskExecMonitor;

    private String taskExecDocs;

    private Double taskExecRate;

    private Integer taskExecDate;
}