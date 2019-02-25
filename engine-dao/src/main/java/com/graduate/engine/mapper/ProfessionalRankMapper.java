package com.graduate.engine.mapper;

import com.graduate.engine.model.ProfessionalRank;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRankMapper {
    int deleteByPrimaryKey(Integer profId);

    int insert(ProfessionalRank record);

    int insertSelective(ProfessionalRank record);

    ProfessionalRank selectByPrimaryKey(Integer profId);

    int updateByPrimaryKeySelective(ProfessionalRank record);

    int updateByPrimaryKey(ProfessionalRank record);
}