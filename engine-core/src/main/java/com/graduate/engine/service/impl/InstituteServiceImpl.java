package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.IndustryMapper;
import com.graduate.engine.mapper.InstituteMapper;
import com.graduate.engine.model.Industry;
import com.graduate.engine.model.Institute;
import com.graduate.engine.service.InstituteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class InstituteServiceImpl extends ServiceImpl<InstituteMapper, Institute> implements InstituteService {
    @Resource
    private InstituteMapper instituteMapper;
    @Resource
    private IndustryMapper industryMapper;

    @Transactional(rollbackFor = Exception.class)
    public void stopByPrimaryKey(String instId) {
        Institute institute = this.getById(instId);
        institute.setStop(true);
        this.updateById(institute);
    }

    public Institute getInstById(String instId) {
        Institute institute = this.getById(instId);
        int industryId = institute.getIndustryId();
        Industry industry = industryMapper.selectById(industryId);
        //todo 如果分类没有怎么做？插入时判断/这里判断
        institute.setIndusName(industry.getIndusName());
        institute.setIndusShort(industry.getIndusShort());
        return institute;
    }

    public List<Institute> getInstitutes() {
        //todo 考虑加入学会行业分类信息
        return instituteMapper.queryAllInst();
    }

    public List<String> getInstList() {
        return instituteMapper.queryAllInstList();
    }

}
