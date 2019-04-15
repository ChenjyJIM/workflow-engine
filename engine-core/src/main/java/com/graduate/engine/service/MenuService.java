package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Menu;
import com.graduate.engine.model.viewobject.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu> {

    /**
     * 获取用户菜单列表
     */
    List<MenuVo> getUserMenuList(Long userId);

    /**
     * 根据父ID查找子菜单
     */
    List<Menu> queryListByParentId(Long userId);

    void delete(Long menuId);

}
