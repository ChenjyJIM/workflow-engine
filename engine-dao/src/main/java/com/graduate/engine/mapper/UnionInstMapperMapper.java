package com.graduate.engine.mapper;

import com.graduate.engine.model.UnionInstMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UnionInstMapperMapper {
    int deleteByPrimaryKey(Integer unionInstId);

    int insert(UnionInstMapper record);

    int insertSelective(UnionInstMapper record);

    UnionInstMapper selectByPrimaryKey(Integer unionInstId);

    int updateByPrimaryKeySelective(UnionInstMapper record);

    int updateByPrimaryKey(UnionInstMapper record);
}