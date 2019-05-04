package com.graduate.engine.model.viewobject;

import lombok.Data;

@Data
public class ActivitySubDto {
    private Integer index;
    private Long personId;
    private Boolean status;
    private String actSubChargerDuty;
    private Boolean actSubChargerEditor;
    private Integer actSubChargerOrder;
}
