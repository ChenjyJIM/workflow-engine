package com.graduate.engine.mapper;

import com.graduate.engine.model.InstPeriod;

public interface InstPeriodMapper {
    int deleteByPrimaryKey(Integer instPeriodId);

    int insert(InstPeriod record);

    int insertSelective(InstPeriod record);

    InstPeriod selectByPrimaryKey(Integer instPeriodId);

    int updateByPrimaryKeySelective(InstPeriod record);

    int updateByPrimaryKey(InstPeriod record);
}