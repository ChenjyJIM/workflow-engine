package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.UnionInstMapper;

import java.util.List;

public interface UnionInstMapperService extends IService<UnionInstMapper> {
    void saveOrUpdate(Long unionId, List<Long> instituteIdList);

    List<Long> getInstIdByUnionId(Long unionId);

    void stopByUnionIdInstId(Long unionId,Long instId);
}
