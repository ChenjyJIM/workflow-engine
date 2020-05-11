package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.PersonMemberApplicationRecord;

import java.util.List;

/**
 * <p>
 * 个人会员申请记录表 Mapper 接口
 * </p>
 *
 * @author lianglili
 * @since 2019-05-09
 */
public interface PersonMemberApplicationRecordMapper extends BaseMapper<PersonMemberApplicationRecord> {
    List<PersonMemberApplicationRecord> selectByApplicationId(Long applicationId);
}
