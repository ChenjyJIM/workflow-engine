package com.graduate.engine.mapper;

import com.graduate.engine.model.Institute;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteMapper {
    int deleteByPrimaryKey(Integer instId);

    int insert(Institute record);

    int insertSelective(Institute record);

    Institute selectByPrimaryKey(Integer instId);

    int updateByPrimaryKeySelective(Institute record);

    int updateByPrimaryKey(Institute record);
}