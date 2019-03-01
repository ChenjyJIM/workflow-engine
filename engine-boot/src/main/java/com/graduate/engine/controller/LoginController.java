package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.model.Login;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        try {
            String name = login.getLoginName();
            Login userInDataBase = loginService.findByName(name);
            if (userInDataBase == null) {
                return ResponseResult.buildError("用户不存在！");
            }
            if (!loginService.comparePassword(login, userInDataBase)) {
                return ResponseResult.buildError("密码错误！");
            }
            String token = loginService.getToken(userInDataBase);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            return ResponseResult.buildSuccess(jsonObject);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    /**
     * 用户注册接口
     * @param login
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody Login login) {
        try{
            if (login == null || login.getLoginName() == null) {
                return ResponseResult.buildError("参数错误！");
            }
            if (loginService.findByName(login.getLoginName()) != null) {
                return ResponseResult.buildError("用户名已存在！");
            }
            if (1 == loginService.add(login)) {
                return ResponseResult.buildSuccess("注册成功！");
            }else {
                return ResponseResult.buildError("注册失败！");
            }
        }
        catch (Exception e){
            return ResponseResult.buildError("系统异常！");
        }
    }
}
