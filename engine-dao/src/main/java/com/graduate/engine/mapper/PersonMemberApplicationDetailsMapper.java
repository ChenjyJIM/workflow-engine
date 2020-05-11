package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.PersonMemberApplicationDetails;

/**
 * <p>
 * 个人会员申请详细信息表 Mapper 接口
 * </p>
 *
 * @author lianglili
 * @since 2019-05-09
 */
public interface PersonMemberApplicationDetailsMapper extends BaseMapper<PersonMemberApplicationDetails> {

    PersonMemberApplicationDetails selectByApplicationId(Long applicationId);
}
