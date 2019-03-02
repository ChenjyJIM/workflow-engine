package com.graduate.engine.mapper;

import com.graduate.engine.model.PersonRoleMapper;

public interface PersonRoleMapperMapper {
    int deleteByPrimaryKey(Integer personRoleId);

    int insert(PersonRoleMapper record);

    int insertSelective(PersonRoleMapper record);

    PersonRoleMapper selectByPrimaryKey(Integer personRoleId);

    int updateByPrimaryKeySelective(PersonRoleMapper record);

    int updateByPrimaryKey(PersonRoleMapper record);
}