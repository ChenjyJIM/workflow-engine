package com.graduate.engine.mapper;

import com.graduate.engine.model.InstPeriodPerson;

public interface InstPeriodPersonMapper {
    int deleteByPrimaryKey(Integer instPeriodPersonId);

    int insert(InstPeriodPerson record);

    int insertSelective(InstPeriodPerson record);

    InstPeriodPerson selectByPrimaryKey(Integer instPeriodPersonId);

    int updateByPrimaryKeySelective(InstPeriodPerson record);

    int updateByPrimaryKey(InstPeriodPerson record);
}