package com.graduate.engine.mapper;

import com.graduate.engine.model.CompanyMember;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMemberMapper {
    int deleteByPrimaryKey(Integer compId);

    int insert(CompanyMember record);

    int insertSelective(CompanyMember record);

    CompanyMember selectByPrimaryKey(Integer compId);

    int updateByPrimaryKeySelective(CompanyMember record);

    int updateByPrimaryKey(CompanyMember record);
}