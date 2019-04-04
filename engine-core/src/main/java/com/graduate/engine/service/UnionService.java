package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Union;

public interface UnionService extends IService<Union> {
    void stopByPrimaryKey(String unionId);
}
