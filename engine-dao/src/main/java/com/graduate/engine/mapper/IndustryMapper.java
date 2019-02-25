package com.graduate.engine.mapper;

import com.graduate.engine.model.Industry;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryMapper {
    int deleteByPrimaryKey(Integer industryId);

    int insert(Industry record);

    int insertSelective(Industry record);

    Industry selectByPrimaryKey(Integer industryId);

    int updateByPrimaryKeySelective(Industry record);

    int updateByPrimaryKey(Industry record);
}