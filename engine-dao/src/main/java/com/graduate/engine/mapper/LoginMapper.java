package com.graduate.engine.mapper;

import com.graduate.engine.model.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    int addLoginUser(Login login);

    Login findLoginUser(Login login);

    /**
     * 修改密码接口
     * @param login
     * @return
     */
    int updatePassword(Login login);
}
