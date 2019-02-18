package com.graduate.engine.mapper;

import com.graduate.engine.model.TaskCheckPoint;

public interface TaskCheckPointMapper {
    int deleteByPrimaryKey(Integer taskCheckPointId);

    int insert(TaskCheckPoint record);

    int insertSelective(TaskCheckPoint record);

    TaskCheckPoint selectByPrimaryKey(Integer taskCheckPointId);

    int updateByPrimaryKeySelective(TaskCheckPoint record);

    int updateByPrimaryKey(TaskCheckPoint record);
}