package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TaskCheckPointVo implements Serializable {

    private Long taskCheckPointId;

    private Long taskId;

    private String taskCheckPointName;

    private String taskCheckPointMemo;

    private Long taskCheckPointDate;
}
