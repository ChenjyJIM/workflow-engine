package com.graduate.engine.mapper;

import com.graduate.engine.model.InstSubPeriod;
import org.springframework.stereotype.Repository;

@Repository
public interface InstSubPeriodMapper {
    int deleteByPrimaryKey(Integer instSubPeriodId);

    int insert(InstSubPeriod record);

    int insertSelective(InstSubPeriod record);

    InstSubPeriod selectByPrimaryKey(Integer instSubPeriodId);

    int updateByPrimaryKeySelective(InstSubPeriod record);

    int updateByPrimaryKey(InstSubPeriod record);
}