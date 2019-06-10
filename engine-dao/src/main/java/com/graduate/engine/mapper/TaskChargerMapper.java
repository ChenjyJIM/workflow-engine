package com.graduate.engine.mapper;

import com.graduate.engine.model.TaskCharger;
import com.graduate.engine.request.TaskQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskChargerMapper {
    int deleteByPrimaryKey(Integer taskChargerId);

    int insert(TaskCharger record);

    int insertSelective(TaskCharger record);

    TaskCharger selectByPrimaryKey(Integer taskChargerId);

    int updateByPrimaryKeySelective(TaskCharger record);

    int updateByPrimaryKey(TaskCharger record);

    /**
     * 根据任务id获取到主要任务负责人
     *
     * @param taskId
     * @return
     */
    TaskCharger getMainTaskCharger(Long taskId);

    /**
     * 根据负责人id查询出所有负责的任务
     *
     * @param query
     * @return
     */
    List<TaskCharger> getByPersonId(TaskQuery query);

    long count(TaskQuery query);
}
