package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

@Data
public class ActivityTree {

    /**
     * 这个活动的id
     */
    private Long actId;

    /**
     * 当前任务的id
     */

    private Long id;

    /**
     * 父亲任务的id
     */
    private Long parentId;

    //以上用于组装树

    private Long instId;

    private Long instSubId;

    private Long personId;

    private String actName;

    private String actShort;

    private String actEngName;

    private Integer industryId;

    private Long actDate;

    private Long actDateFrom;

    private Long actDateTo;

    private String actAddress;

    private Integer actPriority;

    private Long actStatusId;

    private String actMemo;

    private Boolean publish;

    private Boolean stop;

    // 展示数据

    private String personName;

    private String industryName;

    private String date;

    private String dateFrom;

    private String dateTo;

    private String actStatusName;

    private String duty;

    private List<ActivityTree> children;

    private List<MilestoneVo> milestoneVos;

    private List<TaskVo> tasks;


}
