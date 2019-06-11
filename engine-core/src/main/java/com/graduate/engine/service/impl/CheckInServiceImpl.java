package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.CheckInMapper;
import com.graduate.engine.model.CheckIn;
import com.graduate.engine.model.SignUp;
import com.graduate.engine.service.CheckInService;
import com.graduate.engine.service.SignUpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    @Resource
    SignUpService signUpService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyActSignUp(Long signId, Long loginId) {
        CheckIn checkIn = new CheckIn();
        checkIn.setSignId(signId);
        checkIn.setLoginIn(loginId);
        this.save(checkIn);
        SignUp signUp = signUpService.getById(signId);
        if (signUp.getActTotalNum()<=signUp.getSignUpNum()) {
            throw new RuntimeException("名额已满");
        }
        signUp.setCheckInNum(signUp.getSignUpNum()+1);
        signUpService.updateById(signUp);
    }

    @Override
    public CheckIn getBySignUpId(Long signId) {
        return baseMapper.getBySignId(signId);
    }
}
