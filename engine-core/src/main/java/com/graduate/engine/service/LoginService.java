package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Login;
import com.graduate.engine.model.PersonMember;
import com.graduate.engine.model.PersonMemberApplicationDetails;
import com.graduate.engine.model.viewobject.UserInfoVo;
import com.graduate.engine.request.PasswordChangeRequest;
import com.graduate.engine.request.RegisterRequest;
import com.graduate.engine.utils.PageUtils;

import java.text.ParseException;
import java.util.Map;

public interface LoginService extends IService<Login> {
    /**
     * 注册临时账户
     */
    int addGuest(RegisterRequest registerMemberRequest);

    /**
     * 注册个人会员账户
     */
    String addPersonMember(PersonMemberApplicationDetails details);

    /**
     * 添加个人用户信息
     */
    Login addUser(UserInfoVo userInfoVo) throws ParseException;

    /**
     * 更新个人用户信息
     */
    void updateUser(UserInfoVo userInfoVo) throws ParseException;

    /**
     * 获取个人用户信息
     */
    UserInfoVo getUser(Long userId);

    Login findByName(String name);

    Login findById(Long id);

    boolean comparePassword(Login loginUser, Login userInDataBase);

    String getToken(Login login);

    /**
     * 更新访客最后登录时间
     */
    int updateLastLogin(Long guestId);

    int passwordChange(PasswordChangeRequest request);

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 停用账户
     */
    void deleteBatch(Long[] userIds);
}
