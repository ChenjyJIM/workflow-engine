package com.graduate.engine.request;

import lombok.Data;

@Data
public class TaskQuery extends PageQuery {
    private String taskName;
    private Long personId;
    private Boolean editable;
    private Boolean sortByPriority;
}
