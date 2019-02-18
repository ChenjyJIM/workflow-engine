package com.graduate.engine.mapper;

import com.graduate.engine.model.PersonTitle;

public interface PersonTitleMapper {
    int deleteByPrimaryKey(Integer personTitleId);

    int insert(PersonTitle record);

    int insertSelective(PersonTitle record);

    PersonTitle selectByPrimaryKey(Integer personTitleId);

    int updateByPrimaryKeySelective(PersonTitle record);

    int updateByPrimaryKey(PersonTitle record);
}