package com.graduate.engine.service;

import com.graduate.engine.model.Login;

public interface LoginService {
    int add(Login login);

    Login findByName(String name);

    Login findById(Long id);

    boolean comparePassword(Login loginUser, Login userInDataBase);

    String getToken(Login login);
}
