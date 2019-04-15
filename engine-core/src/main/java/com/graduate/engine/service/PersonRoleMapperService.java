package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.PersonRoleMapper;

public interface PersonRoleMapperService extends IService<PersonRoleMapper> {

    int deleteBatch(Long[] roleIds);
}
