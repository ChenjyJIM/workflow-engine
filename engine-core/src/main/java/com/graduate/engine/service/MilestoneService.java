package com.graduate.engine.service;

import com.graduate.engine.model.viewobject.MilestoneVo;
import com.graduate.engine.request.MilestoneRequest;

import java.util.List;

public interface MilestoneService {

    /**
     * 新增（子）活动里程碑
     * @return
     */
    boolean addMilestone(MilestoneRequest request);

    /**
     * 修改里程碑
     * @param request
     * @return
     */
    boolean modifiyMilestone(MilestoneRequest request);

    /**
     * 删除里程碑
     * @param id
     * @param type
     * @return
     */
    boolean deleteMilestone(Long id, String type);


    /**
     * 获取里程碑
     * @param id
     * @param type
     * @return
     */
    List<MilestoneVo> getMilestone(Long id, String type);
}
