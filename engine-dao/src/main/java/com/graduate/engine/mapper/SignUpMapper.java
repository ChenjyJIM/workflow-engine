package com.graduate.engine.mapper;

import com.graduate.engine.model.SignUp;

public interface SignUpMapper {
    int deleteByPrimaryKey(Integer signId);

    int insert(SignUp record);

    int insertSelective(SignUp record);

    SignUp selectByPrimaryKey(Integer signId);

    int updateByPrimaryKeySelective(SignUp record);

    int updateByPrimaryKey(SignUp record);
}