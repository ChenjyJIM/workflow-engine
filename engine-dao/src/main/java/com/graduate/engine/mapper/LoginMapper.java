package com.graduate.engine.mapper;

import com.graduate.engine.model.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    int addLoginUser(Login login);

    Login findLoginUser(Login login);
}
