package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.annotation.CurrentUser;
import com.graduate.engine.annotation.LoginRequired;
import com.graduate.engine.model.Login;
import com.graduate.engine.request.RegisterRequest;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jimmy
 */
@RequestMapping("/authentication")
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * 用户登录接口
     * @param login
     * @return token
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody Login login) {
        // TODO 访客登录和用户登录判断，访客更新登录时间。
        try {
            if (login == null) {
                ResponseResult.buildError("入参错误，请检查！");
            }
            String name = login.getLoginName();
            Login userInDataBase = loginService.findByName(name);
            if (userInDataBase == null) {
                return ResponseResult.buildError("用户不存在！");
            }
            if (!loginService.comparePassword(login, userInDataBase)) {
                return ResponseResult.buildError("密码错误！");
            }
            if (userInDataBase.getGuestId() != null) {
               // 更新访客的最后登录时间
                System.out.println(loginService.updateLastLogin(userInDataBase.getGuestId()));
            }
            String token = loginService.getToken(userInDataBase);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            jsonObject.put("type", userInDataBase.getPersonId() != null ? 1 : 2);
            return ResponseResult.buildSuccess(jsonObject);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    /**
     * 用户注册接口
     * @param  request
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterRequest request) {
        try {
            if (request == null || request.getLoginName() == null) {
                return ResponseResult.buildError("参数错误！");
            }
            if (loginService.findByName(request.getLoginName()) != null) {
                return ResponseResult.buildError("账号已存在！");
            }
            if (1 == loginService.add(request)) {
                return ResponseResult.buildSuccess("注册成功！");
            } else {
                return ResponseResult.buildError("注册失败！");
            }
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/test")
    @LoginRequired
    public Object testCurrentUser(@CurrentUser Login login) {
        return login;
    }
}
