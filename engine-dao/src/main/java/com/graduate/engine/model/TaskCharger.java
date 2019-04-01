package com.graduate.engine.model;

import lombok.Data;

@Data
public class TaskCharger {
    private Long taskChargerId;

    private Long taskId;

    private Long personId;

    private Byte taskChargerOrder;

    private Boolean taskChargerEditer;

    private Long instId;

    private String taskChargerDuty;

    private Boolean stop;
}
