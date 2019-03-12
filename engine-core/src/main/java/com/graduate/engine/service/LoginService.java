package com.graduate.engine.service;

import com.graduate.engine.model.Login;
import com.graduate.engine.request.RegisterRequest;

public interface LoginService {
    int add(RegisterRequest registerMemberRequest);

    Login findByName(String name);

    Login findById(Long id);

    boolean comparePassword(Login loginUser, Login userInDataBase);

    String getToken(Login login);
}
