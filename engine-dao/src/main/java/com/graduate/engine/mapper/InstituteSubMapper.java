package com.graduate.engine.mapper;

import com.graduate.engine.model.InstituteSub;

public interface InstituteSubMapper {
    int deleteByPrimaryKey(Integer instSubId);

    int insert(InstituteSub record);

    int insertSelective(InstituteSub record);

    InstituteSub selectByPrimaryKey(Integer instSubId);

    int updateByPrimaryKeySelective(InstituteSub record);

    int updateByPrimaryKey(InstituteSub record);
}