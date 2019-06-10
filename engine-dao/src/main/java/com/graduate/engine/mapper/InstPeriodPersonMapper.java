package com.graduate.engine.mapper;

import com.graduate.engine.model.InstPeriodPerson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstPeriodPersonMapper {
    int deleteByPrimaryKey(Integer instPeriodPersonId);

    int insert(InstPeriodPerson record);

    int insertSelective(InstPeriodPerson record);

    InstPeriodPerson selectByPrimaryKey(Integer instPeriodPersonId);

    int updateByPrimaryKeySelective(InstPeriodPerson record);

    int updateByPrimaryKey(InstPeriodPerson record);

    /**
     * 根据学会历届id得到历届学会的负责人
     *
     * @param instPeriodId
     * @return
     */
    List<InstPeriodPerson> getByInstPeriodId(Long instPeriodId);
}
