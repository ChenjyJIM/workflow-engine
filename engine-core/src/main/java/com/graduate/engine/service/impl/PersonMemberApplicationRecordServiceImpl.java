package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.model.PersonMemberApplicationRecord;
import com.graduate.engine.mapper.PersonMemberApplicationRecordMapper;
import com.graduate.engine.service.PersonMemberApplicationRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 个人会员申请记录表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
@Service
public class PersonMemberApplicationRecordServiceImpl extends ServiceImpl<PersonMemberApplicationRecordMapper, PersonMemberApplicationRecord> implements PersonMemberApplicationRecordService {

    @Resource
    private PersonMemberApplicationRecordMapper recordMapper;

    @Override
    public List<PersonMemberApplicationRecord> selectByApplicationId(Long applicationId) {
        return recordMapper.selectByApplicationId(applicationId);
    }
}
