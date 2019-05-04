package com.graduate.engine.mapper;

import com.graduate.engine.model.Education;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationMapper {
    int deleteByPrimaryKey(Integer eduId);

    int insert(Education record);

    int insertSelective(Education record);

    Education selectByPrimaryKey(Integer eduId);

    int updateByPrimaryKeySelective(Education record);

    int updateByPrimaryKey(Education record);

    List<Education> getAll();
}
