package com.graduate.engine.service;

import com.graduate.engine.request.TaskRequest;

public interface TaskService {

    Long addTask(TaskRequest request);

    boolean modifyTask(TaskRequest request);

    int deleteTask(Long taskId);
}
