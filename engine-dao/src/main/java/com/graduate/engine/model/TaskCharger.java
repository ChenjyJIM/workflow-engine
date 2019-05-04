package com.graduate.engine.model;

import lombok.Data;

@Data
public class TaskCharger {
    private Long taskChargerId;

    private Long taskId;

    private Long personId;

    private Integer taskChargerOrder;

    private Boolean taskChargerEditor;

    private Long instId;

    private String taskChargerDuty;

    private Boolean stop;
}
