package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.Union;

import java.util.List;

public interface UnionMapper extends BaseMapper<Union> {
    /**
     * 查询未停用学会列表
     */
    List<Union> getUnionList();

}
