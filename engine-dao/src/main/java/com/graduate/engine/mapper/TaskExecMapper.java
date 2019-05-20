package com.graduate.engine.mapper;

import com.graduate.engine.model.TaskExec;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskExecMapper {
    int deleteByPrimaryKey(Long taskExecId);

    int insert(TaskExec record);

    int insertSelective(TaskExec record);

    TaskExec selectByPrimaryKey(Long taskExecId);

    int updateByPrimaryKeySelective(TaskExec record);

    int updateByPrimaryKey(TaskExec record);

    /**
     * 通过任务id获取到执行阶段
     * @param taskId
     * @return
     */
    List<TaskExec> getByTaskId(Long taskId);
}
