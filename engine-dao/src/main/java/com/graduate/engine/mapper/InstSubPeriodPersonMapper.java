package com.graduate.engine.mapper;

import com.graduate.engine.model.InstSubPeriodPerson;
import org.springframework.stereotype.Repository;

@Repository
public interface InstSubPeriodPersonMapper {
    int deleteByPrimaryKey(Integer instSubPeriodPersonId);

    int insert(InstSubPeriodPerson record);

    int insertSelective(InstSubPeriodPerson record);

    InstSubPeriodPerson selectByPrimaryKey(Integer instSubPeriodPersonId);

    int updateByPrimaryKeySelective(InstSubPeriodPerson record);

    int updateByPrimaryKey(InstSubPeriodPerson record);

    InstSubPeriodPerson getMainPersonByPeriodId(Long instSubPeriodId);
}
