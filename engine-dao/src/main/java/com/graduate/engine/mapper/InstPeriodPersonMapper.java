package com.graduate.engine.mapper;

import com.graduate.engine.model.InstPeriodPerson;
import org.springframework.stereotype.Repository;

@Repository
public interface InstPeriodPersonMapper {
    int deleteByPrimaryKey(Integer instPeriodPersonId);

    int insert(InstPeriodPerson record);

    int insertSelective(InstPeriodPerson record);

    InstPeriodPerson selectByPrimaryKey(Integer instPeriodPersonId);

    int updateByPrimaryKeySelective(InstPeriodPerson record);

    int updateByPrimaryKey(InstPeriodPerson record);
}