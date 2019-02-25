package com.graduate.engine.mapper;

import com.graduate.engine.model.ActSubCharger;
import org.springframework.stereotype.Repository;

@Repository
public interface ActSubChargerMapper {
    int deleteByPrimaryKey(Integer actSubChargerId);

    int insert(ActSubCharger record);

    int insertSelective(ActSubCharger record);

    ActSubCharger selectByPrimaryKey(Integer actSubChargerId);

    int updateByPrimaryKeySelective(ActSubCharger record);

    int updateByPrimaryKey(ActSubCharger record);
}