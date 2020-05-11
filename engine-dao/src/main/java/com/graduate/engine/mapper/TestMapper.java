package com.graduate.engine.mapper;

import com.graduate.engine.model.dataobject.PersonMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lianglilimmy
 */
@Repository
public interface TestMapper {
    PersonMember getMemberById(@Param("personId") Long personId);
}
