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
     * 新增一个子活动
     * @param request
     * @return
     */
    Long addSubActivity(ActivitySubRequest request);
}