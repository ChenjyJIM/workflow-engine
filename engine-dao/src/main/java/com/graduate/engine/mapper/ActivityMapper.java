package com.graduate.engine.mapper;

import com.graduate.engine.model.Activity;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer actId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer actId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
}