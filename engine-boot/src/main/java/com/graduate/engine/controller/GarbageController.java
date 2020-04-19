package com.graduate.engine.controller;

import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.model.GarbageDetail;
import com.graduate.engine.model.Industry;
import com.graduate.engine.model.ObjectGarbageMapping;
import com.graduate.engine.request.ObjectQuery;
import com.graduate.engine.response.PagedResult;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.GarbageBizService;
import com.graduate.engine.vaildator.ValidatorUtils;
import com.graduate.engine.vaildator.group.AddGroup;
import com.graduate.engine.vaildator.group.UpdateGroup;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/garbage")
public class GarbageController extends AbstractController {

    @Resource
    private GarbageBizService garbageBizService;


    @PostMapping("/getAllObjects")
    public ResponseResult getAllObjects(@RequestBody ObjectQuery query) {
        try {
            if (query == null) {
                throw new BusinessException("参数不为空！");
            }
            return ResponseResult.buildSuccess(garbageBizService.getAllObjects(query));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @ApiOperation("新增垃圾类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "garbageDetail", value = "垃圾类别", required = true, dataType = "GarbageDetail")
    })
    @PostMapping("/garbageType")
    public ResponseResult addIndustry(@RequestBody GarbageDetail garbageDetail) {
        if (garbageDetail == null) {
            return ResponseResult.buildError("参数异常！");
        }
        if (garbageBizService.save(garbageDetail)) {
            return ResponseResult.buildSuccess("添加垃圾类别成功！");
        }
        return ResponseResult.buildError("添加垃圾类别失败！");
    }

    @ApiOperation("更新垃圾类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "garbageDetail", value = "垃圾类别", required = true, dataType = "GarbageDetail")
    })
    @PutMapping("/garbageType")
    public ResponseResult updateIndustry(@RequestBody GarbageDetail garbageDetail) {
        if (garbageDetail == null || garbageDetail.getId() == null) {
            return ResponseResult.buildError("参数异常！");
        }
        if (garbageBizService.updateById(garbageDetail)) {
            return ResponseResult.buildSuccess("更新成功！");
        }
        return ResponseResult.buildError("更新失败！");
    }


    @ApiOperation("停用垃圾类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "garbageId", value = "垃圾分类代号", required = true, dataType = "Long")
    })
    @DeleteMapping("/delete/{garbageId}")
    public ResponseResult updateIndustry(@PathVariable Long garbageId) {
        garbageBizService.stopGarbageType(garbageId);
        return ResponseResult.buildSuccess("垃圾类别停用成功！");
    }


    @ApiOperation("新增垃圾分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectGarbageMapping", value = "垃圾分类", required = true, dataType =
                    "ObjectGarbageMapping")
    })
    @PostMapping("/objectGarbage")
    public ResponseResult addObjectGarbage(@RequestBody ObjectGarbageMapping objectGarbageMapping) {
        if (objectGarbageMapping == null) {
            return ResponseResult.buildError("参数异常！");
        }
        if (garbageBizService.saveObjectGarbageMapping(objectGarbageMapping)) {
            return ResponseResult.buildSuccess("添加垃圾分类成功！");
        }
        return ResponseResult.buildError("添加垃圾类别失败！");
    }



    @ApiOperation("停用垃圾分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectGarbageId", value = "垃圾分类代号", required = true, dataType = "Long")
    })
    @DeleteMapping("/deleteObjectGarbage/{objectGarbageId}")
    public ResponseResult update(@PathVariable Long objectGarbageId) {
        garbageBizService.stopObjectGarbageMapping(objectGarbageId);
        return ResponseResult.buildSuccess("垃圾分类停用成功！");
    }


    @ApiOperation("更新垃圾类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "garbageDetail", value = "垃圾类别", required = true, dataType = "GarbageDetail")
    })
    @PutMapping("/objectGarbage")
    public ResponseResult updateObjectGarbage(@RequestBody ObjectGarbageMapping objectGarbageMapping) {
        if (objectGarbageMapping == null || objectGarbageMapping.getId() == null) {
            return ResponseResult.buildError("参数异常！");
        }
        if (garbageBizService.updateObjectGarbageMappingById(objectGarbageMapping)) {
            return ResponseResult.buildSuccess("更新成功！");
        }
        return ResponseResult.buildError("更新失败！");
    }
}
