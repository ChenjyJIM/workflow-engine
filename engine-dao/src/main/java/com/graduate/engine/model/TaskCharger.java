package com.graduate.engine.model;

import lombok.Data;

@Data
public class TaskCharger {
    private Integer taskChargerId;

    private Integer taskId;

    private Integer personId;

    private Byte taskChargerOrder;

    private Boolean taskChargerEditer;

    private Integer instId;

    private String taskChargerDuty;

    private Boolean stop;
}