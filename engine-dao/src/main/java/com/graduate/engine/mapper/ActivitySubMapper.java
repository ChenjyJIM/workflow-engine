package com.graduate.engine.mapper;

import com.graduate.engine.model.ActivitySub;

public interface ActivitySubMapper {
    int deleteByPrimaryKey(Integer actSubId);

    int insert(ActivitySub record);

    int insertSelective(ActivitySub record);

    ActivitySub selectByPrimaryKey(Integer actSubId);

    int updateByPrimaryKeySelective(ActivitySub record);

    int updateByPrimaryKey(ActivitySub record);
}