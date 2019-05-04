package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Institute;
import com.graduate.engine.model.Union;

import java.util.List;

public interface UnionService extends IService<Union> {

    void stopByPrimaryKey(String unionId);

    /**
     * 查询未停用学会列表
     */
    List<Union> getUnionList();

    /**
     * 根据学联体ID获取所含学会
     */
    List<Institute> getInstList(Long unionId);
}
