package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.model.viewobject.TaskCheckPointVo;
import com.graduate.engine.request.TaskCheckPointRequest;
import com.graduate.engine.request.TaskExecRequest;
import com.graduate.engine.request.TaskQuery;
import com.graduate.engine.request.TaskRequest;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController extends AbstractController{

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

    @PostMapping("/modifyTask")
    public ResponseResult modifyTask(@RequestBody TaskRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(taskService.modifyTask(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/deleteTask")
    public ResponseResult deleteTask(Long taskId) {
        if (taskId == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(1 == taskService.deleteTask(taskId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/addTaskCheckPoint")
    public ResponseResult addTaskCheckPoint(@RequestBody TaskCheckPointRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误");
        }
        try {
            return ResponseResult.build(1 == taskService.addTaskCheckPoint(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常");
        }
    }


    @PostMapping("/modifyTaskCheckPoint")
    public ResponseResult modifyTaskCheckPoint(@RequestBody TaskCheckPointRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误");
        }
        try {
            return ResponseResult.build(1 == taskService.modifyTaskCheckPoint(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/deleteTaskCheckPoint")
    public ResponseResult deleteTaskCheckPoint(Long taskCheckPointId) {
        if (taskCheckPointId == null) {
            return ResponseResult.buildError("参数不为空！");
        }
        try {
            return ResponseResult.build(1 == taskService.deleteTaskCheckPoint(taskCheckPointId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/getTaskCheckPoint")
    public ResponseResult getTaskCheckPoint(Long taskId) {
        if (taskId == null) {
            return ResponseResult.buildError("参数错误");
        }
        try {
            return ResponseResult.buildSuccess(taskService.getTaskCheckPoint(taskId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/getOwnerTask")
    public ResponseResult getOwnerTask(@RequestBody TaskQuery query) {
        if (query == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.buildSuccess(taskService.getOwnerTask(query, getPersonId()));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/addTaskExec")
    public ResponseResult addTaskExec(@RequestBody TaskExecRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(taskService.addTaskExec(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/modifyTaskExec")
    public ResponseResult modifyTaskExec(@RequestBody TaskExecRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(taskService.modifyTaskExec(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }
    @GetMapping("/getTaskExec")
    public ResponseResult getTaskExec(Long taskId) {
        if (taskId == null) {
            return ResponseResult.buildError("参数错误");
        }
        try {
            return ResponseResult.buildSuccess(taskService.getTaskExec(taskId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

}
