package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.SignUpMapper;
import com.graduate.engine.model.SignUp;
import com.graduate.engine.service.MessageService;
import com.graduate.engine.service.SignUpService;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.utils.PageUtils;
import com.graduate.engine.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * <p>
 * 报名管理 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-06-09
 */
@Service
public class SignUpServiceImpl extends ServiceImpl<SignUpMapper, SignUp> implements SignUpService {
    @Resource
    MessageService messageService;

    @Override
    public void startSignUp(SignUp sign) throws ParseException {
        //保存活动报名表
        sign.setSignDateFrom(DateUtils.getTimeStampByUTC(sign.getFormatSignDateFrom()));
        sign.setSignDateTo(DateUtils.getTimeStampByUTC(sign.getFormatSignDateTo()));
        this.save(sign);
        //发送通知
        messageService.sendMessageToAll("活动通知",sign.getSignForm());
    }

    @Override
    public void updateSignUp(SignUp sign) throws ParseException {
        //更新活动报名表
        sign.setSignDateFrom(DateUtils.getTimeStampByUTC(sign.getFormatSignDateFrom()));
        sign.setSignDateTo(DateUtils.getTimeStampByUTC(sign.getFormatSignDateTo()));
        this.updateById(sign);
        //发送通知
        messageService.sendMessageToAll("活动修改通知",sign.getSignForm());
    }

    @Override
    public void stopSignUp(Long signId, String message) {
        //停止活动报名
        SignUp signUp = this.getById(signId);
        signUp.setStop(true);
        signUp.setSignForm(message);
        this.updateById(signUp);
        //发送通知
        messageService.sendMessageToAll("活动停止通知",message);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        boolean withoutStop = Boolean.parseBoolean((String) params.get("withoutStop"));
        String signName = (String) params.get("signName");
        String industryId = (String) params.get("industryId");

        IPage<SignUp> page = this.page(
                new Query<SignUp>().getPage(params),
                new QueryWrapper<SignUp>()
                        .eq(withoutStop, "stop", false)
                        .like(StringUtils.isNotBlank(signName), "sign_name", signName)
                        .in(StringUtils.isNotBlank(industryId), "industry_id", industryId)
        );

        return new PageUtils(page);
    }
}
