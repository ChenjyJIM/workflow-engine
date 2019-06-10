package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.CheckInMapper;
import com.graduate.engine.model.CheckIn;
import com.graduate.engine.model.SignUp;
import com.graduate.engine.service.CheckInService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报名签到 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-06-09
 */
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements CheckInService {

    @Override
    public void applyActSignUp(Long signId, Long loginId) {
        CheckIn checkIn = new CheckIn();
        checkIn.setSignId(signId);
        checkIn.setLoginIn(loginId);
        this.save(checkIn);
    }

    @Override
    public CheckIn getBySignUpId(Long signId) {
        return baseMapper.getBySignId(signId);
    }
}
