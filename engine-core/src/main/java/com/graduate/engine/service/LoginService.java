package com.graduate.engine.service;

import com.graduate.engine.model.Login;
import com.graduate.engine.request.PasswordChangeRequest;
import com.graduate.engine.request.RegisterRequest;

public interface LoginService {
    int add(RegisterRequest registerMemberRequest);

    Login findByName(String name);

    Login findById(Long id);

    boolean comparePassword(Login loginUser, Login userInDataBase);

    String getToken(Login login);

    /**
     * 更新访客最后登录时间
     */
    int updateLastLogin(Long guestId);

    int passwordChange(PasswordChangeRequest request);
}
