package com.graduate.engine.controller;

import com.graduate.engine.annotation.LoginRequired;
import com.graduate.engine.model.viewobject.InfoListVo;
import com.graduate.engine.model.viewobject.InstPeriodVo;
import com.graduate.engine.model.viewobject.InstSubsVo;
import com.graduate.engine.model.viewobject.InstituteVo;
import com.graduate.engine.request.InstituteQuery;
import com.graduate.engine.request.InstituteRequest;
import com.graduate.engine.response.PagedResult;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.InstituteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jimmy
 */
@RestController
@RequestMapping("/institute")
public class InstituteController {

    @Resource
    private InstituteService instituteService;


    @PostMapping("/getAllInstitute")
    public ResponseResult getAllInstitute(@RequestBody InstituteQuery query) {
        try {
            if (query == null) {
                return ResponseResult.buildError("参数不能为空！");
            }
            PagedResult<InstituteVo> instituteResults = instituteService.getAllInstitute(query);
            return ResponseResult.buildSuccess(instituteResults);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }


    @PostMapping("/instModify")
    public ResponseResult instModify(@RequestBody InstituteRequest request) {
        try {
            if (request.getInstId() == null) {
                return ResponseResult.buildError("参数不能为空！");
            }
            return ResponseResult.build(instituteService.instModify(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/getInstituteByInstId")
    public ResponseResult getInstituteByInstId(Long instId) {
        try {
            if (instId == null) {
                return ResponseResult.buildError("参数不为空！");
            }
            return ResponseResult.buildSuccess(instituteService.getInstituteByInstId(instId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/getInfoList")
    public ResponseResult getInfoList(Long instId) {
        try {
            if (instId == null) {
                return ResponseResult.buildError("参数不为空！");
            }
            InfoListVo result = instituteService.getInfoList(instId);
            return ResponseResult.buildSuccess(result);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }


    @GetMapping("/getInstSubs")
    public ResponseResult getInstSubs(Long instId) {
        try {
            if (instId == null) {
                return ResponseResult.buildError("参数不能为空！");
            }
            //通过instId获取到简单的分会信息
            List<InstSubsVo> results = instituteService.getSimpleSubsByInstId(instId);
            return ResponseResult.buildSuccess(results);

        } catch (Exception e) {
            return ResponseResult.buildError("系统异常");
        }
    }

    @GetMapping("getPeriodByInstId")
    public ResponseResult getPeriodByInstId(Long instId) {
        try {
            if (instId == null) {
                return ResponseResult.buildError("参数不能为空！");
            }
            //通过instId获取到简单的分会信息
            List<InstPeriodVo> results = instituteService.getPeriodByInstId(instId);
            return ResponseResult.buildSuccess(results);

        } catch (Exception e) {
            return ResponseResult.buildError("系统异常");
        }
    }
}
