package com.graduate.engine.model;

import com.graduate.engine.enums.PriorityEnum;
import com.graduate.engine.model.viewobject.ActivityTree;
import com.graduate.engine.model.viewobject.TreeData;
import com.graduate.engine.utils.DateUtils;
import lombok.Data;

import javax.annotation.Resource;

@Data
public class ActivitySub {

    private Long actSubId;

    private Long actId;

    private Long instId;

    private Long instSubId;

    private String actSubName;

    private String actSubShort;

    private String actSubEngName;

    private Integer industryId;

    private Long actSubDateFrom;

    private Long actSubDateTo;

    private String actSubAddress;

    private Integer actSubPriority;

    private Long actSubStatusId;

    private String actSubMemo;

    private Long actSubSplit;

    private Long actSubMerge;

    private Long actSubFatherId;

    private String actSubRestartMemo;

    private Boolean publish;

    private Boolean stop;

    public ActivityTree packetActivityTree() {
        ActivityTree result = new ActivityTree();
        result.setActName(this.actSubName);
        result.setIndustryId(this.industryId);
        result.setActAddress(this.actSubAddress);
        result.setActDateFrom(this.actSubDateFrom);
        result.setActDateTo(this.actSubDateTo);
        result.setActEngName(this.actSubEngName);
        result.setId(this.actSubId);
        result.setActMemo(this.actSubMemo);
        result.setActShort(this.actSubShort);
        result.setActPriority(this.actSubPriority);
        result.setParentId(this.actSubFatherId != null ? this.actSubFatherId : this.actId);
        result.setInstId(this.instId);
        result.setInstSubId(this.instSubId);
        result.setStop(this.stop);
        result.setPublish(this.publish);
        result.setActStatusId(this.actSubStatusId);
        return result;
    }

    public TreeData packetTreeData() {
        TreeData result = new TreeData();
        result.setId(this.actSubId);
        result.setName(this.actSubName);
        result.setIntroduction("介绍");
        result.setType("子活动");
        result.setStartTime(DateUtils.getDateStrByTimestamp(this.getActSubDateFrom()).substring(0, 10));
        result.setEndTime(DateUtils.getDateStrByTimestamp(this.getActSubDateTo()).substring(0, 10));
        result.setParentId(this.actSubFatherId != null ? this.actSubFatherId : this.actId);
        result.setPublish(this.publish);
        result.setPriority(PriorityEnum.getByCode(this.actSubPriority).desc());
        return result;
    }
}
