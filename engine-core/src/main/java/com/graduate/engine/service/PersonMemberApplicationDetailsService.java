package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.PersonMemberApplicationDetails;

/**
 * <p>
 * 个人会员申请详细信息表 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
public interface PersonMemberApplicationDetailsService extends IService<PersonMemberApplicationDetails> {

    PersonMemberApplicationDetails selectByApplicationId(Long applicationId);

}
