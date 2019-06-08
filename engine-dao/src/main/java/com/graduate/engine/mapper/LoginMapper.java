package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.Login;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginMapper extends BaseMapper<Login> {
    int addLoginUser(Login login);

    Login findLoginUser(Login login);

    /**
     * 修改密码接口
     * @param login
     * @return
     */
    int updatePassword(Login login);

    /**
     * 获取所有用户列表
     */
    List<Long> getAllUsers();

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单
     * @param userId  用户ID
     */
    List<Long> queryAllMenu(Long userId);


    /**
     * 批量停用账户
     */
    void stopBatch(Long[] userIds);

    /**
     * 通过人员id获取到对应账号
     * @param personId
     * @return
     */
    Login getLoginIdByPersonId(Long personId);
}
