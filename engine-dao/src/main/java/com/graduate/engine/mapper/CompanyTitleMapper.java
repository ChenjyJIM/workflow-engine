package com.graduate.engine.mapper;

import com.graduate.engine.model.CompanyTitle;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTitleMapper {
    int deleteByPrimaryKey(Integer compTitleId);

    int insert(CompanyTitle record);

    int insertSelective(CompanyTitle record);

    CompanyTitle selectByPrimaryKey(Integer compTitleId);

    int updateByPrimaryKeySelective(CompanyTitle record);

    int updateByPrimaryKey(CompanyTitle record);
}