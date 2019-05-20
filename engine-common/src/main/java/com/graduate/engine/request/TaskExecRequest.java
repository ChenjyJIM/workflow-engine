package com.graduate.engine.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 新增任务执行阶段前端请求模板
 * @author jimmy
 */
@Data
public class TaskExecRequest implements Serializable {
    private Long taskExecId;

    private Long taskId;

    private String taskExecName;

    private Long taskExecStatus;

    private String taskExecReport;

    private String taskExecMonitor;

    private String taskExecDocs;

    private BigDecimal taskExecRate;

    private String taskExecDate;
}
