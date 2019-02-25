package com.graduate.engine.mapper;

import com.graduate.engine.model.PersonMember;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMemberMapper {
    int deleteByPrimaryKey(Integer personId);

    int insert(PersonMember record);

    int insertSelective(PersonMember record);

    PersonMember selectByPrimaryKey(Integer personId);

    int updateByPrimaryKeySelective(PersonMember record);

    int updateByPrimaryKey(PersonMember record);
}