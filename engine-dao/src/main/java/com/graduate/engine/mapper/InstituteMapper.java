package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.Institute;

import java.util.List;

public interface InstituteMapper extends BaseMapper<Institute> {

    /**
     * 查询所有未停用的学会信息
     */
    List<Institute> queryAllInst();

    /**
     * 查询未停用学会列表
     */
    List<String> queryAllInstList();
}
