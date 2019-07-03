package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.model.viewobject.ActivityVo;
import com.graduate.engine.request.ActivityQuery;
import com.graduate.engine.request.ActivityRequest;
import com.graduate.engine.request.ActivitySubRequest;
import com.graduate.engine.response.PagedResult;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 活动相关rest接口
 *
 * @author jimmy
 */
@RestController
@RequestMapping("/activity")
public class ActivityController extends AbstractController{
    @Resource
    private ActivityService activityService;

    @PostMapping("/queryActivity")
    public ResponseResult queryActivity(@RequestBody ActivityQuery query) {
        try {
            if (query == null) {
                return ResponseResult.buildError("参数不能为空！");
            }
            PagedResult<ActivityVo> instituteResults = activityService.queryActivity(query);
            return ResponseResult.buildSuccess(instituteResults);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @PostMapping("/backdoor_mongo_message_query")
    public void backDoorMongoQuery(@RequestBody ActivityQuery query) {
        templateRequest(() -> {
            if (query == null) {
                throw new BusinessException("参数不为空！");
            }
            return activityService.queryActivity(query);
        });
    }


    @GetMapping("/getTreeData")
    public ResponseResult getTreeData(Long actId) {
        if (actId == null) {
            return ResponseResult.buildError("参数不为空！");
        }
        try {
            return ResponseResult.buildSuccess(activityService.getTreeData(actId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/getActivityById")
    public ResponseResult getActivityById(Long actId) {
        if (actId == null) {
            return ResponseResult.buildError("参数不为空！");
        }
        try {
            return ResponseResult.buildSuccess(activityService.getActivityById(actId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

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

    @GetMapping("/deleteActivity")
    public ResponseResult deleteActivity(Long actId) {
        if (actId == null) {
            return ResponseResult.buildError("参数错误！");
        }
        return ResponseResult.build(1 == activityService.deleteActivity(actId));
    }

    @GetMapping("/publishActivity")
    public ResponseResult publishActivity(Long actId) {
        if (actId == null) {
            return ResponseResult.buildError("参数错误！");
        }

        try {
            return ResponseResult.build(activityService.publishActivity(actId));
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

    @PostMapping("/modifyActivitySub")
    public ResponseResult modifyActivitySub(@RequestBody ActivitySubRequest request) {
        if (request == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(activityService.modifyActivitySub(request));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/deleteActivitySub")
    public ResponseResult deleteActivitySub(Long actSubId) {
        if (actSubId == null) {
            return ResponseResult.buildError("参数错误！");
        }
        return ResponseResult.build(1 == activityService.deleteActivitySub(actSubId));
    }

    /**
     * 发布活动
     *
     * @param actSubId
     * @return
     */
    @GetMapping("/publishActivitySub")
    public ResponseResult publishActivitySub(Long actSubId) {
        if (actSubId == null) {
            return ResponseResult.buildError("参数错误！");
        }
        try {
            return ResponseResult.build(activityService.publishActivitySub(actSubId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

}
