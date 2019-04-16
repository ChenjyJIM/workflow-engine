package com.graduate.engine.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaskExec {
    private Long taskExecId;

    private Long taskId;

    private String taskExecName;

    private Integer taskExecStatus;

    private String taskExecReport;

    private String taskExecMonitor;

    private String taskExecDocs;

    private BigDecimal taskExecRate;

    private Long taskExecDate;
}
