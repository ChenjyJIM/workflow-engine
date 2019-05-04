package com.graduate.engine.model;

import lombok.Data;

@Data
public class ActSubCharger {
    private Long actSubChargerId;

    private Long actSubId;

    private Long personId;

    private Integer actSubChargerOrder;

    private Boolean actSubChargerEditor;

    private Long instId;

    private String actSubChargerDuty;

    private Boolean stop;

}
