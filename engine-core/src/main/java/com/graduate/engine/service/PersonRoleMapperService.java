package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.PersonRoleMapper;

public interface PersonRoleMapperService extends IService<PersonRoleMapper> {

    int deleteBatch(Long[] roleIds);

    void addGuestRole(Long loginId);

    void addUserRole(Long loginId, Long roleId);

    void updateUserRole(Long loginId, Long roleId);

    PersonRoleMapper selectByPersonId(Long personId);
}
