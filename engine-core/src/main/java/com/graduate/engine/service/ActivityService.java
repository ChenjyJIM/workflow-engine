package com.graduate.engine.service;

import com.graduate.engine.request.ActivityRequest;
import com.graduate.engine.request.ActivitySubRequest;

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
}
