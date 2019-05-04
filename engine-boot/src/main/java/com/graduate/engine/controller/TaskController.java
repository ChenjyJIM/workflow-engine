package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.request.TaskRequest;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping("/addTask")
    public ResponseResult addTask(@RequestBody TaskRequest request) {
        if (request.getTaskName() == null) {
            return ResponseResult.buildError("参数异常！");
        }
        try {
            JSONObject result = new JSONObject();
            result.put("taskId", taskService.addTask(request));
            return ResponseResult.buildSuccess(result);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }
}
