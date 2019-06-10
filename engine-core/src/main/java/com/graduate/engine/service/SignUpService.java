package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.SignUp;
import com.graduate.engine.utils.PageUtils;

import java.text.ParseException;
import java.util.Map;

/**
 * <p>
 * 报名管理 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-06-09
 */
public interface SignUpService extends IService<SignUp> {
    //发起活动报名
    void startSignUp(SignUp sign) throws ParseException;
    //修改活动报名信息
    void updateSignUp(SignUp sign) throws ParseException;
    //停止活动报名
    void stopSignUp(Long signId,String message);
    //活动报名列表，可选项
    //signName 活动名称
    //industryId 项目行业分类
    //withoutStop 列表中是否不包含停用活动报名
    PageUtils queryPage(Map<String, Object> params);
}
