package com.graduate.engine.mapper;

import com.graduate.engine.model.ActivityStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityStatusMapper {
    int deleteByPrimaryKey(Long actStatusId);

    int insert(ActivityStatus record);

    int insertSelective(ActivityStatus record);

    ActivityStatus selectByPrimaryKey(Long actStatusId);

    int updateByPrimaryKeySelective(ActivityStatus record);

    int updateByPrimaryKey(ActivityStatus record);
}
