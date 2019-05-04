package com.graduate.engine.service;

import com.graduate.engine.model.Login;

import java.util.Set;

public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    Login queryUser(Long userId);
}
