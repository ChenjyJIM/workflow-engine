package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.PersonRoleMapper;

import java.util.List;

public interface PersonRoleMapperMapper extends BaseMapper<PersonRoleMapper> {

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

    /**
     * 根据角色ID返回该角色的所有userId
     */
    List<Long> selectAllUserIds(Long[] roleIds);

    PersonRoleMapper selectByPersonId(Long personId);
}
