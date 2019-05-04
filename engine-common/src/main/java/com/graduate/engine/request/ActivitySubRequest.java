package com.graduate.engine.request;

import com.graduate.engine.model.viewobject.ActivitySubDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新增子活动请求模板
 * @author jimmy
 */
@Data
public class ActivitySubRequest implements Serializable {
    private Long actId;
    private String actSubName;
    private String actSubShort;
    private String actSubEngName;
    private String actSubAddress;
    private String actSubMemo;
    private Integer actSubPriority;
    private String actSubDateFrom;
    private String actSubDateTo;
    private Integer industryId;
    private String parentType;
    private Long parentId;
    /**
     * 前端返回学会id和分会id
     * 格式：['1','2'] 第一个为学会id，第二个为分会id
     */
    private List<String> ids;

    private List<ActivitySubDto> personChargers;
}
