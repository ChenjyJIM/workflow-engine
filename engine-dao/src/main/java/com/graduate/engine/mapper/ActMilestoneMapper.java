package com.graduate.engine.mapper;

import com.graduate.engine.model.ActMilestone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActMilestoneMapper {
    int deleteByPrimaryKey(Long milestoneId);

    int insert(ActMilestone record);

    int insertSelective(ActMilestone record);

    ActMilestone selectByPrimaryKey(Long milestoneId);

    int updateByPrimaryKeySelective(ActMilestone record);

    int updateByPrimaryKey(ActMilestone record);

    List<ActMilestone> getById(Long actId);

}
