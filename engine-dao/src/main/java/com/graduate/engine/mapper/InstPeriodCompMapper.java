package com.graduate.engine.mapper;

import com.graduate.engine.model.InstPeriodComp;
import org.springframework.stereotype.Repository;

@Repository
public interface InstPeriodCompMapper {
    int deleteByPrimaryKey(Integer instPeriodCompId);

    int insert(InstPeriodComp record);

    int insertSelective(InstPeriodComp record);

    InstPeriodComp selectByPrimaryKey(Integer instPeriodCompId);

    int updateByPrimaryKeySelective(InstPeriodComp record);

    int updateByPrimaryKey(InstPeriodComp record);
}