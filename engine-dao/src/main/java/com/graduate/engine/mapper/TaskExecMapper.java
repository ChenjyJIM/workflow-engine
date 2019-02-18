package com.graduate.engine.mapper;

import com.graduate.engine.model.TaskExec;

public interface TaskExecMapper {
    int deleteByPrimaryKey(Integer taskExecId);

    int insert(TaskExec record);

    int insertSelective(TaskExec record);

    TaskExec selectByPrimaryKey(Integer taskExecId);

    int updateByPrimaryKeySelective(TaskExec record);

    int updateByPrimaryKey(TaskExec record);
}