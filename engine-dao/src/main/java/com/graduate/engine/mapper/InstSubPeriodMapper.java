package com.graduate.engine.mapper;

import com.graduate.engine.model.InstSubPeriod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstSubPeriodMapper {

    /**
     * 通过instSubId查询出历届的分会
     */
    List<InstSubPeriod> getByInstSubId(Long instSubId);

    /**
     * 查询出分会当前届，目前根据id倒序拿到最新的id
     * @param instSubId
     * @return
     */
    InstSubPeriod getCurrentPeriod(Long instSubId);

    int deleteByPrimaryKey(Integer instSubPeriodId);

    int insert(InstSubPeriod record);

    int insertSelective(InstSubPeriod record);

    InstSubPeriod selectByPrimaryKey(Integer instSubPeriodId);

    int updateByPrimaryKeySelective(InstSubPeriod record);

    int updateByPrimaryKey(InstSubPeriod record);
}
