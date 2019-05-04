package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService extends IService<Role> {

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long roleId);

    List<Role> getRoleList(Map<String, Object> map);

}
