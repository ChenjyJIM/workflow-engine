package com.graduate.engine.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graduate.engine.mapper.LoginMapper;
import com.graduate.engine.model.Login;
import com.graduate.engine.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 注册登录相关service
 * @author jimmy
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;

    public int add(Login login) {
        //TODO 该接口还需绑定 personMember信息和guest信息 目前只是简单的插入
        String passwordHash = passwordToHash(login.getLoginPassword());
        Login newLogin = new Login();
        newLogin.setLoginName(login.getLoginName());
        newLogin.setLoginPassword(passwordHash);
        return loginMapper.addLoginUser(newLogin);
    }

    public Login findByName(String name) {
        Login login = new Login();
        login.setLoginName(name);
        return loginMapper.findLoginUser(login);
    }

    public Login findById(Long id) {
        Login login = new Login();
        login.setLoginId(id);
        return loginMapper.findLoginUser(login);
    }
    //用于密码加密存储
    private String passwordToHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }

    public boolean comparePassword(Login loginUser, Login userInDataBase) {
        return passwordToHash(loginUser.getLoginPassword())      // 将用户提交的密码转换为 hash
                .equals(userInDataBase.getLoginPassword()); // 数据库中的 password 已经是 hash，不用转换
    }

    /**
     * 登录后返回token给前端，前端请求时将token放到header里
     * @param login
     * @return
     */
    public String getToken(Login login) {
        String token = "";
        try {
            token = JWT.create()
                    .withAudience(login.getLoginId().toString())          // 将 user id 保存到 token 里面
                    .sign(Algorithm.HMAC256(login.getLoginPassword()));   // 以 password 作为 token 的密钥
        } catch (UnsupportedEncodingException ignore) {
        }
        return token;
    }
}
