package com.graduate.engine.mapper;

import com.graduate.engine.model.Union;
import org.springframework.stereotype.Repository;

@Repository
public interface UnionMapper {
    int deleteByPrimaryKey(Integer unionId);

    int insert(Union record);

    int insertSelective(Union record);

    Union selectByPrimaryKey(Integer unionId);

    int updateByPrimaryKeySelective(Union record);

    int updateByPrimaryKey(Union record);
}