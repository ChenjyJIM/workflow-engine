package com.graduate.engine.model.viewobject;

import lombok.Data;

/**
 * 用于增加子活动时分配任务负责人
 *
 * @author lianglili
 */
@Data
public class ActivitySubCharger {
    private Integer index;
    private Long personId;
    private Boolean status;
    private String actSubChargerDuty;
    private Boolean actSubChargerEditor;
    private Integer actSubChargerOrder;
}
