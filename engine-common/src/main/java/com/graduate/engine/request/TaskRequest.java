package com.graduate.engine.request;

import com.graduate.engine.model.viewobject.TaskChargerDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新增任务前端请求模板
 *
 * @author jimmy
 */
@Data
public class TaskRequest implements Serializable {
    private Long actId;
    private Long taskId;
    private String taskName;
    private String taskShort;
    private String taskEngName;
    private String taskMemo;
    private Integer taskPriority;
    private String taskDateFrom;
    private String taskDateTo;
    private String parentType;
    private Long parentId;
    private Long personId;
    private String duty;
    private List<TaskChargerDto> personChargers;
}
