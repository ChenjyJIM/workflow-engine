package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.RoleMenu;

import java.util.List;


public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

    List<Long> queryMenuIdList(Long roleId);
}
