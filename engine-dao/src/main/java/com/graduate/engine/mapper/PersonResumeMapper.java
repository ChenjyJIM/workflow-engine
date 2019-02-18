package com.graduate.engine.mapper;

import com.graduate.engine.model.PersonResume;

public interface PersonResumeMapper {
    int deleteByPrimaryKey(Integer personResumeId);

    int insert(PersonResume record);

    int insertSelective(PersonResume record);

    PersonResume selectByPrimaryKey(Integer personResumeId);

    int updateByPrimaryKeySelective(PersonResume record);

    int updateByPrimaryKey(PersonResume record);
}