package com.graduate.engine.controller;

import com.graduate.engine.model.CheckIn;
import com.graduate.engine.model.SignUp;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.CheckInService;
import com.graduate.engine.service.IndustryService;
import com.graduate.engine.service.SignUpService;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.utils.PageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RequestMapping("/sign")
@RestController
public class SignUpController extends AbstractController {
    @Resource
    SignUpService signUpService;
    @Resource
    CheckInService checkInService;
    @Resource
    IndustryService industryService;

    @ApiOperation("发起活动报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sign", value = "活动报名信息", required = true, dataType = "SignUp")
    })
    @PostMapping("/starting")
    public ResponseResult startSignUp(@RequestBody SignUp sign) throws ParseException {
        signUpService.startSignUp(sign);
        return ResponseResult.buildSuccess("发起报名成功");
    }

    @ApiOperation("更新活动报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sign", value = "活动报名信息", required = true, dataType = "SignUp")
    })
    @PutMapping("/starting")
    public ResponseResult updateSignUp(@RequestBody SignUp sign) throws ParseException {
        signUpService.updateSignUp(sign);
        return ResponseResult.buildSuccess("更新信息成功");
    }

    @ApiOperation("停止活动报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signId", value = "活动报名ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "message", value = "停止通知", required = true, dataType = "String")
    })
    @DeleteMapping("/starting/{signId}/{message}")
    public ResponseResult stopSignUp(@PathVariable("signId") Long signId,@PathVariable("message") String message) {
        signUpService.stopSignUp(signId,message);
        return ResponseResult.buildSuccess("停用成功");
    }

    @ApiOperation("活动报名列表")
    @GetMapping("/starting")
    public ResponseResult getSignUpList(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = signUpService.queryPage(params);
        List<SignUp> signUps = (List<SignUp>) pageUtils.getList();
        for (SignUp signUp : signUps) {
            signUp.setIndustryName(industryService.getById(signUp.getIndustryId()).getIndusName());
            signUp.setFormatSignDateFrom(DateUtils.getDateStrByTimestamp(signUp.getSignDateFrom()));
            signUp.setFormatSignDateTo(DateUtils.getDateStrByTimestamp(signUp.getSignDateTo()));
        }
        pageUtils.setList(signUps);
        return ResponseResult.buildSuccess(pageUtils);
    }

    @ApiOperation("活动报名详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signId", value = "活动报名ID", required = true, dataType = "Long")
    })
    @GetMapping("/starting/{signId}")
    public ResponseResult getSignUp(@PathVariable("signId") Long signId) {
        SignUp signUp = signUpService.getById(signId);
        signUp.setIndustryName(industryService.getById(signUp.getIndustryId()).getIndusName());
        signUp.setFormatSignDateFrom(DateUtils.getDateStrByTimestamp(signUp.getSignDateFrom()));
        signUp.setFormatSignDateTo(DateUtils.getDateStrByTimestamp(signUp.getSignDateTo()));
        return ResponseResult.buildSuccess(signUp);
    }

    @ApiOperation("开始签到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signId", value = "活动报名ID", required = true, dataType = "Long")
    })
    @PutMapping("/starting/check/{signId}")
    public ResponseResult startCheckIn(@PathVariable("signId") Long signId) {
        SignUp signUp = signUpService.getById(signId);
        signUp.setStartSignUp(true);
        signUpService.updateById(signUp);
        return ResponseResult.buildSuccess("活动开始签到");
    }

    @ApiOperation("停止签到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signId", value = "活动报名ID", required = true, dataType = "Long")
    })
    @DeleteMapping("/starting/check/{signId}")
    public ResponseResult stopCheckIn(@PathVariable("signId") Long signId) {
        SignUp signUp = signUpService.getById(signId);
        signUp.setStartSignUp(false);
        signUpService.updateById(signUp);
        return ResponseResult.buildSuccess("活动停止签到");
    }

    @ApiOperation("报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signId", value = "活动报名ID", required = true, dataType = "Long")
    })
    @PostMapping("/application/{signId}")
    public ResponseResult applyActSignUp(@PathVariable("signId") Long signId) {
        try {
            checkInService.applyActSignUp(signId,getUserId());
        }catch (RuntimeException e) {
            return ResponseResult.buildError(e.getMessage());
        }

        return ResponseResult.buildSuccess("报名成功");
    }

    @ApiOperation("报名活动详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signId", value = "活动报名ID", required = true, dataType = "Long")
    })
    @GetMapping("/application/{signId}")
    public ResponseResult getCheckInApplication(@PathVariable("signId") Long signId) {
        SignUp signUp = signUpService.getById(signId);
        signUp.setIndustryName(industryService.getById(signUp.getIndustryId()).getIndusName());
        signUp.setFormatSignDateFrom(DateUtils.getDateStrByTimestamp(signUp.getSignDateFrom()));
        signUp.setFormatSignDateTo(DateUtils.getDateStrByTimestamp(signUp.getSignDateTo()));
        CheckIn checkIn = checkInService.getBySignUpId(signId);
        if (checkIn==null){
            signUp.setStatus(0L);
        } else if (checkIn.getSignUp()){
            signUp.setCheckId(checkIn.getCheckId());
            signUp.setStatus(2L);
        } else {
            signUp.setCheckId(checkIn.getCheckId());
            signUp.setStatus(1L);
        }
        return ResponseResult.buildSuccess(signUp);
    }

    @ApiOperation("活动签到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkId", value = "报名签到表ID", required = true, dataType = "Long")
    })
    @PutMapping("/application/{checkId}")
    public ResponseResult checkActSignUp(@PathVariable("checkId") Long checkId) {
        CheckIn checkIn = checkInService.getById(checkId);
        checkIn.setSignUp(true);
        SignUp signUp = signUpService.getById(checkIn.getSignId());
        signUp.setCheckInNum(signUp.getCheckInNum()+1);
        checkInService.updateById(checkIn);
        signUpService.updateById(signUp);
        return ResponseResult.buildSuccess("签到成功");
    }

}
