package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.Industry;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryMapper extends BaseMapper<Industry> {
    int deleteByPrimaryKey(Integer industryId);

    //与mybatis plus方法冲突
//    int insert(Industry record);

    int insertSelective(Industry record);

    Industry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Industry record);

    int updateByPrimaryKey(Industry record);

    List<Industry> getAll();

    List<Industry> getWithoutStop();
}
