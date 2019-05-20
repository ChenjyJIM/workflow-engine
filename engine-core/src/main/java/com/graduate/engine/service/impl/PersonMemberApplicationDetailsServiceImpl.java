package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.PersonMemberApplicationDetailsMapper;
import com.graduate.engine.model.PersonMemberApplicationDetails;
import com.graduate.engine.service.PersonMemberApplicationDetailsService;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 个人会员申请详细信息表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
@Service
public class PersonMemberApplicationDetailsServiceImpl extends ServiceImpl<PersonMemberApplicationDetailsMapper, PersonMemberApplicationDetails> implements PersonMemberApplicationDetailsService {
    @Resource
    PersonMemberApplicationDetailsMapper detailsMapper;

    @Override
    public PersonMemberApplicationDetails selectByApplicationId(Long applicationId) {
        PersonMemberApplicationDetails details = detailsMapper.selectByApplicationId(applicationId);
        details.setFormatBirthday(DateUtils.getDateStrByTimestamp(details.getBirthday()));
        return details;
    }
}
