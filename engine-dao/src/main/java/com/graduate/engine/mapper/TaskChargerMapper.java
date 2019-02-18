package com.graduate.engine.mapper;

import com.graduate.engine.model.TaskCharger;

public interface TaskChargerMapper {
    int deleteByPrimaryKey(Integer taskChargerId);

    int insert(TaskCharger record);

    int insertSelective(TaskCharger record);

    TaskCharger selectByPrimaryKey(Integer taskChargerId);

    int updateByPrimaryKeySelective(TaskCharger record);

    int updateByPrimaryKey(TaskCharger record);
}