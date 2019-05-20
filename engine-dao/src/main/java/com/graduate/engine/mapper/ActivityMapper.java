package com.graduate.engine.mapper;

import com.graduate.engine.model.Activity;
import com.graduate.engine.request.ActivityQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper {
    int deleteByPrimaryKey(Long actId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long actId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    Integer count(ActivityQuery query);

    List<Activity> queryActivity(ActivityQuery query);
}
