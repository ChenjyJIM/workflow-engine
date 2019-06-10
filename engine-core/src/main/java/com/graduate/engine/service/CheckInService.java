package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.CheckIn;

/**
 * <p>
 * 报名签到 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-06-09
 */
public interface CheckInService extends IService<CheckIn> {
    void applyActSignUp(Long signId,Long loginId);

    //根据signUpID查询是否有报名签到
    CheckIn getBySignUpId(Long signId);
}
