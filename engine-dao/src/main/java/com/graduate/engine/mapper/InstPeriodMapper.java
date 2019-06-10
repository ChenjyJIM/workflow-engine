package com.graduate.engine.mapper;

import com.graduate.engine.model.InstPeriod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstPeriodMapper {
    /**
     * 通过instId查询出历届的学会
     */
    List<InstPeriod> getByInstId(Long instId);

    /**
     * 得到当前届
     *
     * @param instId
     * @return
     */
    InstPeriod getCurrentPeriod(Long instId);

    int deleteByPrimaryKey(Integer instPeriodId);

    int insert(InstPeriod record);

    int insertSelective(InstPeriod record);

    InstPeriod selectByPrimaryKey(Integer instPeriodId);

    int updateByPrimaryKeySelective(InstPeriod record);

    int updateByPrimaryKey(InstPeriod record);
}
