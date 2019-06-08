package com.graduate.engine.mapper;

import com.graduate.engine.model.ActSubMilestone;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActSubMilestoneMapper {
    int deleteByPrimaryKey(Long subMilestoneId);

    int insert(ActSubMilestone record);

    int insertSelective(ActSubMilestone record);

    ActSubMilestone selectByPrimaryKey(Long subMilestoneId);

    int updateByPrimaryKeySelective(ActSubMilestone record);

    int updateByPrimaryKey(ActSubMilestone record);

    List<ActSubMilestone> getById(Long actSubId);


    List<ActSubMilestone> getActSubMilestoneByCondition(@Param("now") Long now, @Param("type") Integer type);

}
