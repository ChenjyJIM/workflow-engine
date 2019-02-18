package com.graduate.engine.mapper;

import com.graduate.engine.model.InstSubPeriodComp;

public interface InstSubPeriodCompMapper {
    int deleteByPrimaryKey(Integer instSubPeriodCompId);

    int insert(InstSubPeriodComp record);

    int insertSelective(InstSubPeriodComp record);

    InstSubPeriodComp selectByPrimaryKey(Integer instSubPeriodCompId);

    int updateByPrimaryKeySelective(InstSubPeriodComp record);

    int updateByPrimaryKey(InstSubPeriodComp record);
}