package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.PersonRoleMapper;

public interface PersonRoleMapperMapper extends BaseMapper<PersonRoleMapper> {

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
