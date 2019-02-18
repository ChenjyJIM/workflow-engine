package com.graduate.engine.mapper;

import com.graduate.engine.model.ActMilestone;

public interface ActMilestoneMapper {
    int deleteByPrimaryKey(Integer milestoneId);

    int insert(ActMilestone record);

    int insertSelective(ActMilestone record);

    ActMilestone selectByPrimaryKey(Integer milestoneId);

    int updateByPrimaryKeySelective(ActMilestone record);

    int updateByPrimaryKey(ActMilestone record);
}