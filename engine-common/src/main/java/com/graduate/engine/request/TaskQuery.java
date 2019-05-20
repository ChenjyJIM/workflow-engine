package com.graduate.engine.request;

import lombok.Data;

@Data
public class TaskQuery extends PageQuery {
    private Long personId;
}
