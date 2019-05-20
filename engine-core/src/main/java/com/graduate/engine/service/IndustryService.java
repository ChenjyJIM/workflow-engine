package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Industry;

import java.util.List;

public interface IndustryService extends IService<Industry> {
    void stopByPrimaryKey(String industryId);

    List<Industry> getIndustryWithoutStop();
}
