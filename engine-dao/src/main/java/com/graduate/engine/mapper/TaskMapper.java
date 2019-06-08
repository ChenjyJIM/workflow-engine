package com.graduate.engine.mapper;

import com.graduate.engine.model.Task;
import com.graduate.engine.model.viewobject.TaskVo;
import com.graduate.engine.request.TaskQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {
    int deleteByPrimaryKey(Long taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    /**
     * 查询出直接在总活动下的任务
     * @param actId
     * @return
     */
    List<Task> getByActId(Long actId);

    /**
     * 查询出子活动下的任务
     * @param actSubId
     * @return
     */
    List<Task> getByActSubId(Long actSubId);

    /**
     * 查询所有任务
     * @param taskQuery
     * @return
     */
    List<Task> getAllTasks(TaskQuery taskQuery);

    /**
     * 所有任务数目
     * @param taskQuery
     * @return
     */
    long countAllTasks(TaskQuery taskQuery);
}
