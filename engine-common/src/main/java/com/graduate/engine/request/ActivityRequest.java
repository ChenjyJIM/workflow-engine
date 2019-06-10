package com.graduate.engine.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 新增活动请求模板
 *
 * @author jimmy
 */
@Data
public class ActivityRequest implements Serializable {
    private Long actId;
    private String actName;
    private String actShort;
    private Long personId;
    private String actEngName;
    private String actAddress;
    private String actMemo;
    private Integer actPriority;
    private String actDateFrom;
    private String actDateTo;
    private Integer industryId;
    /**
     * 前端返回学会id和分会id
     * 格式：['1','2'] 第一个为学会id，第二个为分会id
     */
    private List<String> ids;


}
