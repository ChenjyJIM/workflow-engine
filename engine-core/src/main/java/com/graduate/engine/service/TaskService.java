package com.graduate.engine.service;

import com.graduate.engine.model.viewobject.TaskCheckPointVo;
import com.graduate.engine.model.viewobject.TaskExecVo;
import com.graduate.engine.model.viewobject.TaskVo;
import com.graduate.engine.request.TaskCheckPointRequest;
import com.graduate.engine.request.TaskExecRequest;
import com.graduate.engine.request.TaskQuery;
import com.graduate.engine.request.TaskRequest;
import com.graduate.engine.response.PagedResult;

import java.util.List;

public interface TaskService {

    Long addTask(TaskRequest request);

    boolean modifyTask(TaskRequest request);

    int deleteTask(Long taskId);

    /**
     * 新增任务检查点
     * @param request
     * @return
     */
    int addTaskCheckPoint(TaskCheckPointRequest request);

    /**
     * 修改检查点
     * @param request
     * @return
     */
    int modifyTaskCheckPoint(TaskCheckPointRequest request);

    /**
     * 根据taskCheckPointId 删除检查点
     * @param taskCheckPointId
     * @return
     */
    int deleteTaskCheckPoint(Long taskCheckPointId);

    /**
     * 根据任务id拿到所有检查点
     * @param taskId
     * @return
     */
    List<TaskCheckPointVo> getTaskCheckPoint(Long taskId);

    /**
     * 拿到分配给当前用户的任务
     * @param query
     * @return
     */
    PagedResult<TaskVo> getOwnerTask(TaskQuery query, Long personId);

    /**
     * 新增任务执行阶段
     * @param request
     * @return
     */
    boolean addTaskExec(TaskExecRequest request);

    /**
     * 修改任务执行阶段
     * @param request
     * @return
     */
    boolean modifyTaskExec(TaskExecRequest request);

    List<TaskExecVo> getTaskExec(Long taskId);
}

