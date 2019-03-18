package com.graduate.engine.controller;

import com.graduate.engine.annotation.CurrentUser;
import com.graduate.engine.annotation.LoginRequired;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.model.Login;
import com.graduate.engine.model.viewobject.PersonMemberVo;
import com.graduate.engine.request.MemberModifiedRequest;
import com.graduate.engine.request.PasswordChangeRequest;
import com.graduate.engine.request.TestQuery;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.BaseService;
import com.graduate.engine.service.GuestService;
import com.graduate.engine.service.LoginService;
import com.graduate.engine.service.PersonMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author jimmy
 */
@RequestMapping("/member")
@RestController
public class PersonMemberController {

    @Resource
    private BaseService baseService;

    @Resource
    private PersonMemberService personMemberService;

    @Resource
    private GuestService guestService;

    @Resource
    private LoginService loginService;


    @PostMapping("/getById")
    @LoginRequired
    public ResponseResult getById(@RequestBody TestQuery testQuery) {
        try {
            if (testQuery != null) {
                PersonMemberVo personMemberVo = baseService.getByPersonId(1L);

                return ResponseResult.buildSuccess(personMemberVo);
            } else {
                return ResponseResult.buildError("参数错误！");
            }
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @LoginRequired
    @GetMapping("/getCurrentUser")
    public ResponseResult getCurrentUser(@CurrentUser Login user) {
        try {
            if (user == null) {
                return ResponseResult.buildError("系统异常！user is null");
            }
            Object result = user.getPersonId() != null ?
                    personMemberService.getByPersonId(user.getPersonId()) : guestService.getByGuestId(user.getGuestId());
            return ResponseResult.buildSuccess(result);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @LoginRequired
    @PostMapping("/infoModified")
    public ResponseResult infoModified(@CurrentUser Login user, @RequestBody MemberModifiedRequest memberModifiedRequest) {
        try {
            if (memberModifiedRequest == null || user == null) {
                return ResponseResult.buildError("参数不能为空！");
            }
            memberModifiedRequest.setPersonId(user.getPersonId());
            memberModifiedRequest.setGuestId(user.getGuestId());
            Object result = user.getPersonId() != null ?
                    personMemberService.infoModified(memberModifiedRequest) : guestService.infoModified(memberModifiedRequest);
            return ResponseResult.buildSuccess(result);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @LoginRequired
    @PostMapping("/passwordChange")
    public ResponseResult passwordChange(@CurrentUser Login user, @RequestBody PasswordChangeRequest request) {
        try {
            if (user == null || request == null || user.getLoginId() == null) {
                return ResponseResult.buildError("参数错误！请检查！");
            }
            request.setLoginId(user.getLoginId());
            if (1 == loginService.passwordChange(request)) {
                return ResponseResult.buildSuccess();
            } else {
                return ResponseResult.buildError("修改失败！");
            }
        } catch (BusinessException be) {
            return ResponseResult.buildError(be.getErrMsg());
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }
}
