package com.graduate.engine.mapper;

import com.graduate.engine.model.Login;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 获取所有用户列表
     */
    List<Long> getAllUsers();

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单
     * @param userId  用户ID
     */
    List<Long> queryAllMenu(Long userId);
}
