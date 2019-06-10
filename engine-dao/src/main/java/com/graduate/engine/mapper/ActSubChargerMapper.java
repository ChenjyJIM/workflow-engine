package com.graduate.engine.mapper;

import com.graduate.engine.model.ActSubCharger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActSubChargerMapper {
    int deleteByPrimaryKey(Integer actSubChargerId);

    int insert(ActSubCharger record);

    int insertSelective(ActSubCharger record);

    ActSubCharger selectByPrimaryKey(Integer actSubChargerId);

    int updateByPrimaryKeySelective(ActSubCharger record);

    int updateByPrimaryKey(ActSubCharger record);

    /**
     * 获取到该子活动的核心负责人
     *
     * @param actSubId
     * @return
     */
    ActSubCharger getMainSubCharger(Long actSubId);

    /**
     * 获取到该子活动所有负责人
     *
     * @param actSubId
     * @return
     */
    List<ActSubCharger> getSubChargers(Long actSubId);
}
