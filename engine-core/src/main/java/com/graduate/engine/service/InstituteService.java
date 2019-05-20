package com.graduate.engine.service;

import com.graduate.engine.model.Institute;
import com.graduate.engine.model.viewobject.InfoListVo;
import com.graduate.engine.model.viewobject.InstPeriodVo;
import com.graduate.engine.model.viewobject.InstSubsVo;
import com.graduate.engine.model.viewobject.InstituteVo;
import com.graduate.engine.request.InstituteQuery;
import com.graduate.engine.response.PagedResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Institute;

import java.util.List;

/**
 * 学会相关服务
 */
public interface InstituteService extends IService<Institute>{
    /**
     * 获取所有学会基本信息
     * @param query
     * @return
     */
    PagedResult<InstituteVo> getAllInstitute(InstituteQuery query);


    List<InstSubsVo> getSimpleSubsByInstId(Long instId);

    void stopByPrimaryKey(String instId);

    /**
     * 获取学会信息，包括了学会行业分类信息
     */
    Institute getInstById(Long instId);

    List<Institute> getInstitutes();

    /**
     * 根据学会id获取到历届学会信息
     * @param instId
     * @return
     */
    List<InstPeriodVo> getPeriodByInstId(Long instId);

    /**
     * 通过学会id获取到自定义的学会信息
     * @param instId
     * @return
     */
    InstituteVo getInstituteByInstId(Long instId);

    /**
     * 根据学会id获取到页面所需List
     * @param instId
     * @return
     */
    InfoListVo getInfoList(Long instId);

    List<Institute> getInstListBuInstIds(Long[] instIds);
}
