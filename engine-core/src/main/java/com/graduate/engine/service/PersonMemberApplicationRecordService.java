package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.PersonMemberApplicationRecord;

import java.util.List;

/**
 * <p>
 * 个人会员申请记录表 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
public interface PersonMemberApplicationRecordService extends IService<PersonMemberApplicationRecord> {
    List<PersonMemberApplicationRecord> selectByApplicationId(Long applicationId);
}
