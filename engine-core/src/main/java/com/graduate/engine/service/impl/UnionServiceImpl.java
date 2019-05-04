package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.UnionMapper;
import com.graduate.engine.model.Industry;
import com.graduate.engine.model.Institute;
import com.graduate.engine.model.Union;
import com.graduate.engine.model.UnionInstMapper;
import com.graduate.engine.service.IndustryService;
import com.graduate.engine.service.InstituteService;
import com.graduate.engine.service.UnionInstMapperService;
import com.graduate.engine.service.UnionService;
import com.graduate.engine.utils.DateUtils;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnionServiceImpl extends ServiceImpl<UnionMapper, Union> implements UnionService {
    @Resource
    UnionMapper unionMapper;
    @Resource
    UnionInstMapperService unionInstMapperService;
    @Resource
    InstituteService instituteService;
    @Resource
    IndustryService industryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopByPrimaryKey(String unionId) {
        Union union = this.getById(unionId);
        union.setStop(true);
        this.updateById(union);
    }

    @Override
    public List<Union> getUnionList() {
        return unionMapper.getUnionList();
    }

    @Override
    public List<Institute> getInstList(Long unionId) {
        Long[] unionIds = unionInstMapperService.getInstIdByUnionId(unionId).toArray(new Long[0]);
        if (unionIds.length != 0) {
            return instituteService.getInstListBuInstIds(unionIds)
                    .stream().peek(institute -> {
                        institute.setRegisterTime(
                                DateUtils.getDateStrByTimestamp(institute.getInstRegisterDate()));
                        Industry industry = industryService.getById(institute.getIndustryId());
                        institute.setIndusShort(industry.getIndusShort());
                        institute.setIndusName(industry.getIndusName());
                    }).collect(Collectors.toList());
        } else {
            throw new RuntimeException("没有学会加入该学联体");
        }
    }
}
