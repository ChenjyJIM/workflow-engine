package com.graduate.engine.mapper;

import com.graduate.engine.model.ActivitySub;
import com.graduate.engine.model.viewobject.ActivityVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitySubMapper {
    int deleteByPrimaryKey(Long actSubId);

    int insert(ActivitySub record);

    int insertSelective(ActivitySub record);

    ActivitySub selectByPrimaryKey(Long actSubId);

    int updateByPrimaryKeySelective(ActivitySub record);

    int updateByPrimaryKey(ActivitySub record);

    List<ActivitySub> getByActId(Long actId);
}
