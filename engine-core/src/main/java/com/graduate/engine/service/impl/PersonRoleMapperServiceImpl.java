package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.PersonRoleMapperMapper;
import com.graduate.engine.model.PersonRoleMapper;
import com.graduate.engine.service.PersonRoleMapperService;
import org.springframework.stereotype.Service;

@Service
public class PersonRoleMapperServiceImpl extends ServiceImpl<PersonRoleMapperMapper, PersonRoleMapper> implements PersonRoleMapperService {

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
