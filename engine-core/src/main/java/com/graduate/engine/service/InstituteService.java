package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Institute;

import java.util.List;

public interface InstituteService extends IService<Institute> {

    void stopByPrimaryKey(String instId);

    /**
     * 获取学会信息，包括了学会行业分类信息
     */
    Institute getInstById(String instId);

    List<Institute> getInstitutes();

    List<String> getInstList();
}
