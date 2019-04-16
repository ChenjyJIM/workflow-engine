package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.UnionMapper;
import com.graduate.engine.model.Union;
import com.graduate.engine.service.UnionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnionServiceImpl extends ServiceImpl<UnionMapper, Union> implements UnionService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopByPrimaryKey(String unionId) {
        Union union = this.getById(unionId);
        union.setStop(true);
        this.updateById(union);
    }
}
