package com.graduate.engine.model.viewobject;

import lombok.Data;

@Data
public class TaskChargerDto {

    private Integer index;
    private Long personId;
    private Boolean status;
    private String taskChargerDuty;
    private Boolean taskChargerEditor;
    private Integer taskChargerOrder;
}
