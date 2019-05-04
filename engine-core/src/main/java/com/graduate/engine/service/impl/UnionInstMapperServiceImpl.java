package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.UnionInstMapperMapper;
import com.graduate.engine.model.UnionInstMapper;
import com.graduate.engine.service.UnionInstMapperService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UnionInstMapperServiceImpl extends ServiceImpl<UnionInstMapperMapper, UnionInstMapper> implements UnionInstMapperService {

    @Override
    public void saveOrUpdate(Long unionId, List<Long> instituteIdList) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("union_id",unionId);
        this.removeByMap(map);

        for (Long instituteId : instituteIdList) {
            UnionInstMapper unionInstMapper = new UnionInstMapper();
            unionInstMapper.setUnionId(unionId);
            unionInstMapper.setInstId(instituteId);

            this.save(unionInstMapper);
        }
    }
}