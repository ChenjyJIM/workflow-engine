package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 前端里程碑展示vo
 *
 * @author jimmy
 */
@Getter
@Setter
public class MilestoneVo implements Serializable {

    private Long milestoneId;

    private Long actId;

    private String milestoneName;

    private String milestoneMemo;

    private Long milestoneFrom;

    private Long milestoneTo;

    private String milestoneReport;

    private String milestoneMonitor;
}
