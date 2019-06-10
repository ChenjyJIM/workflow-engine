package com.graduate.engine.mapper;

import com.graduate.engine.model.InstPeriodComp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstPeriodCompMapper {
    int deleteByPrimaryKey(Integer instPeriodCompId);

    int insert(InstPeriodComp record);

    int insertSelective(InstPeriodComp record);

    InstPeriodComp selectByPrimaryKey(Integer instPeriodCompId);

    int updateByPrimaryKeySelective(InstPeriodComp record);

    int updateByPrimaryKey(InstPeriodComp record);

    List<InstPeriodComp> getByInstPeriodId(Long instPeriodId);
}
