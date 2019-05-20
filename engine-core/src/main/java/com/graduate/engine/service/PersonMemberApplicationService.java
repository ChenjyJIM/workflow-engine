package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.PersonMemberApplication;
import com.graduate.engine.model.PersonMemberApplicationDetails;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 个人会员申请表 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-05-09
 */
public interface PersonMemberApplicationService extends IService<PersonMemberApplication> {
    void applyPersonMember(PersonMemberApplicationDetails details, Long userId) throws ParseException;

    List<PersonMemberApplication> selectByLoginId(Long loginId);

    List<PersonMemberApplication> selectReviewApplication(Long initId);

    /**
     * 同意入会申请
     */
    void agreeApplication(Long applicationId, Long editPersonId);

    /**
     * 不同意入会申请
     */
    void disagreeApplication(Long applicationId, String message, Long editPersonId);
}
