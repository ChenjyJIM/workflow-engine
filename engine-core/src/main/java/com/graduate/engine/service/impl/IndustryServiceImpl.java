package com.graduate.engine.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.IndustryMapper;
import com.graduate.engine.model.Industry;
import com.graduate.engine.service.IndustryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry> implements IndustryService {

    @Resource
    IndustryMapper industryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopByPrimaryKey(String industryId) {
        Industry industry = this.getById(industryId);
        industry.setStop(true);
        //todo 停用后关于该分类下的学会分类如何
        this.updateById(industry);
    }

    @Override
    public List<Industry> getIndustryWithoutStop() {
        return industryMapper.getWithoutStop();
    }
}
