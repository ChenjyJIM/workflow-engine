package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.RoleMenu;

import java.util.List;

public interface RoleMenuService extends IService<RoleMenu> {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    int deleteBatch(Long[] roleIds);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
