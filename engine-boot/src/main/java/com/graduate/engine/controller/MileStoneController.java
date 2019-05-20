package com.graduate.engine.controller;

import com.graduate.engine.request.MilestoneRequest;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.MilestoneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/milestone")
public class MileStoneController {

    @Resource
    private MilestoneService milestoneService;

    @PostMapping("/addMilestone")
    public ResponseResult addMilestone(@RequestBody MilestoneRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(milestoneService.addMilestone(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/modifyMilestone")
    public ResponseResult modifyMilestone(@RequestBody MilestoneRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(milestoneService.modifiyMilestone(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/deleteMilestone")
    public ResponseResult deleteMilestone(Long id, String type) {
        if (id == null || StringUtils.isEmpty(type)) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(milestoneService.deleteMilestone(id, type));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/getMilestone")
    public ResponseResult getMilestone(Long id, String type) {
        if (id == null || StringUtils.isEmpty(type)) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.buildSuccess(milestoneService.getMilestone(id, type));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }

    }

}
