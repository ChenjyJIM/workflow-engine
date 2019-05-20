package com.graduate.engine.service;

import com.graduate.engine.model.viewobject.ActivityTree;
import com.graduate.engine.model.viewobject.ActivityVo;
import com.graduate.engine.model.viewobject.MilestoneVo;
import com.graduate.engine.model.viewobject.TreeData;
import com.graduate.engine.request.ActivityQuery;
import com.graduate.engine.request.ActivityRequest;
import com.graduate.engine.request.ActivitySubRequest;
import com.graduate.engine.response.PagedResult;

import java.util.List;

public interface ActivityService {

    /**
     * 新增一个活动
     * @param request
     * @return
     */
    Long addActivity(ActivityRequest request);

    /**
     * 修改活动信息
     * @param request
     * @return
     */
    int modifyActivity(ActivityRequest request);

    /**
     * 根据活动id删除活动（非物理删除）
     * @param actId
     * @return
     */
    int deleteActivity(Long actId);

    /**
     * 根据活动id 发布活动
     */
    boolean publishActivity(Long actId);

    /**
     * 新增一个子活动
     * @param request
     * @return
     */
    Long addSubActivity(ActivitySubRequest request);

    /**
     * 修改子活动信息
     * @param request
     * @return
     */
    boolean modifyActivitySub(ActivitySubRequest request);

    /**
     * 根据子活动id删除子活动（非物理删除）
     * @param actSubId
     * @return
     */
    int deleteActivitySub(Long actSubId);

    /**
     * 根据子活动id 发布子活动
     */
    boolean publishActivitySub(Long actId);


    /**
     * 根据查询条件查询出活动基本信息
     * @param query
     * @return
     */
    PagedResult<ActivityVo> queryActivity(ActivityQuery query);

    /**
     * 返回单个活动信息
     * @param actId
     * @return
     */
    ActivityTree getActivityById(Long actId);

    /**
     * 返回树形结构的数据
     * @param actId
     * @return
     */
    TreeData getTreeData(Long actId);
}

