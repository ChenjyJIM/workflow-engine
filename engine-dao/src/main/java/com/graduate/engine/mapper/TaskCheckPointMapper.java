package com.graduate.engine.mapper;

import com.graduate.engine.model.TaskCheckPoint;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCheckPointMapper {
    int deleteByPrimaryKey(Long taskCheckPointId);

    int insert(TaskCheckPoint record);

    int insertSelective(TaskCheckPoint record);

    TaskCheckPoint selectByPrimaryKey(Long taskCheckPointId);

    int updateByPrimaryKeySelective(TaskCheckPoint record);

    int updateByPrimaryKey(TaskCheckPoint record);

    /**
     * 根据任务id拿到所有检查点
     *
     * @param taskId
     * @return
     */
    List<TaskCheckPoint> getByTaskId(Long taskId);

    List<TaskCheckPoint> getTaskCheckPointByCondition(@Param("now") Long now, @Param("type") Integer type);
}
