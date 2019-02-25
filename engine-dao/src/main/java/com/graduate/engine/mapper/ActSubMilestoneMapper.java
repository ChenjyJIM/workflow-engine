package com.graduate.engine.mapper;

import com.graduate.engine.model.ActSubMilestone;
import org.springframework.stereotype.Repository;

@Repository
public interface ActSubMilestoneMapper {
    int deleteByPrimaryKey(Integer subMilestoneId);

    int insert(ActSubMilestone record);

    int insertSelective(ActSubMilestone record);

    ActSubMilestone selectByPrimaryKey(Integer subMilestoneId);

    int updateByPrimaryKeySelective(ActSubMilestone record);

    int updateByPrimaryKey(ActSubMilestone record);
}