package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Institute;
import com.graduate.engine.model.viewobject.InfoListVo;
import com.graduate.engine.model.viewobject.InstPeriodVo;
import com.graduate.engine.model.viewobject.InstSubsVo;
import com.graduate.engine.model.viewobject.InstituteVo;
import com.graduate.engine.request.InstituteQuery;
import com.graduate.engine.request.InstituteRequest;
import com.graduate.engine.response.PagedResult;

import java.util.List;


@Deprecated
public interface InstituteService extends IService<Institute> {


    PagedResult<InstituteVo> getAllInstitute(InstituteQuery query);


    List<InstSubsVo> getSimpleSubsByInstId(Long instId);

    void stopByPrimaryKey(String instId);

    Institute getInstById(Long instId);

    List<Institute> getInstitutes();

    List<InstPeriodVo> getPeriodByInstId(Long instId);

    InstituteVo getInstituteByInstId(Long instId);

    InfoListVo getInfoList(Long instId);

    List<Institute> getInstListBuInstIds(Long[] instIds);

    boolean instModify(InstituteRequest request);

    boolean instAdd(InstituteRequest request);
}
