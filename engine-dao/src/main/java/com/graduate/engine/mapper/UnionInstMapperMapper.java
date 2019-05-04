package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.UnionInstMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnionInstMapperMapper extends BaseMapper<UnionInstMapper> {
    List<Long> queryInstIdsByUnionId(Long unionId);

    int stopByUnionIdInstId(@Param("unionId") Long unionId, @Param("instId") Long instId);
}
