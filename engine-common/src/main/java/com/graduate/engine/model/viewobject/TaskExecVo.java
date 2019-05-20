package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TaskExecVo {
    private Long taskExecId;

    private Long taskId;

    private String taskExecName;

    private Long taskExecStatus;

    private String taskExecReport;

    private String taskExecMonitor;

    private String taskExecDocs;

    private BigDecimal taskExecRate;

    private String taskExecDate;

    private String taskExecStatusName;

}
