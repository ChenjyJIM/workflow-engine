package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.Institute;
import com.graduate.engine.request.InstituteQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstituteMapper extends BaseMapper<Institute>{

    Long count(InstituteQuery query);

    List<Institute> getInstitutesSelective(InstituteQuery query);


    int deleteByPrimaryKey(Integer instId);

//    int insert(Institute record);

    int insertSelective(Institute record);

    Institute selectByPrimaryKey(Long instId);

    int updateByPrimaryKeySelective(Institute record);

    int updateByPrimaryKey(Institute record);

    /**
     * 查询所有未停用的学会信息
     */
    List<Institute> queryAllInst();

    /**
     * 根据学会id查询学会信息
     */
    List<Institute> queryInstByInstIds(Long[] instIds);

    List<Institute> getByPersonId(@Param("personId") Long personId);
}
