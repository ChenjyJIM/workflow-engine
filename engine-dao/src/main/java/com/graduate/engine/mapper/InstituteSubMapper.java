package com.graduate.engine.mapper;

import com.graduate.engine.model.InstituteSub;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstituteSubMapper {
    List<InstituteSub> getInstSubsByInstId(Long id);

    int deleteByPrimaryKey(Long instSubId);

    int insert(InstituteSub record);

    int insertSelective(InstituteSub record);

    InstituteSub selectByPrimaryKey(Long instSubId);

    int updateByPrimaryKeySelective(InstituteSub record);

    int updateByPrimaryKey(InstituteSub record);
}
