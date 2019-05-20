package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.PersonMemberApplication;

import java.util.List;

/**
 * <p>
 * 个人会员申请表 Mapper 接口
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
public interface PersonMemberApplicationMapper extends BaseMapper<PersonMemberApplication> {
    List<PersonMemberApplication> selectByLoginId(Long loginId);

    List<PersonMemberApplication> selectReviewApplication(Long initId);
}
