package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.request.ActivityRequest;
import com.graduate.engine.request.ActivitySubRequest;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.ActivityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 活动相关rest接口
 * @author jimmy
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @PostMapping("/addActivity")
    public ResponseResult addActivity(@RequestBody ActivityRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            JSONObject result = new JSONObject();
            result.put("actId", activityService.addActivity(request));
                return ResponseResult.buildSuccess(result);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/modifyActivity")
    public ResponseResult modifyActivity(@RequestBody ActivityRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(1 == activityService.modifyActivity(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/addSubActivity")
    public ResponseResult addSubActivity(@RequestBody ActivitySubRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            JSONObject result = new JSONObject();
            result.put("actSubId", activityService.addSubActivity(request));
            return ResponseResult.buildSuccess(result);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

}
