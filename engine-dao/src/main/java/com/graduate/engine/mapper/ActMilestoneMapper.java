package com.graduate.engine.mapper;

import com.graduate.engine.model.ActMilestone;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据时间获取里程碑
     * type为1 > 86400 type为2 < 86400
     *
     * @return
     */
    List<ActMilestone> getActMilestoneByCondition(@Param("now") Long now, @Param("type") Integer type);
}
