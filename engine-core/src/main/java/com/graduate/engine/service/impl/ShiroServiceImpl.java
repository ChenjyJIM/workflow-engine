package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.LoginMapper;
import com.graduate.engine.mapper.MenuMapper;
import com.graduate.engine.model.Login;
import com.graduate.engine.model.Menu;
import com.graduate.engine.service.ShiroService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private MenuMapper menuMapper;

    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == 1){
            List<Menu> menuList = menuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(Menu menu : menuList){
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = loginMapper.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    public Login queryUser(Long userId) {
        Login login = new Login();
        login.setLoginId(userId);
        return loginMapper.findLoginUser(login);
    }

    private boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}
