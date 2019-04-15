package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.LoginMapper;
import com.graduate.engine.mapper.MenuMapper;
import com.graduate.engine.model.Menu;
import com.graduate.engine.model.viewobject.MenuVo;
import com.graduate.engine.service.MenuService;
import com.graduate.engine.service.RoleMenuService;
import com.graduate.engine.utils.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public List<MenuVo> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return queryListByParentId(0L,null);
        }

        List<Long> menuList = loginMapper.queryAllMenu(userId);
        return queryListByParentId(0L,menuList);
    }

    @Override
    public List<Menu> queryListByParentId(Long userId) {
        return menuMapper.queryListByParentId(userId);
    }

    @Override
    public void delete(Long menuId) {
        this.removeById(menuId);
        //删除菜单与角色的关联
        Map<String,Object> map = new HashMap<>();
        map.put("menu_id", menuId);
        roleMenuService.removeByMap(map);
    }

    /**
     * 根据父菜单 ID 获取子菜单
     */
    private List<MenuVo> queryListByParentId(Long parentId,List<Long> menuList) {
        List<Menu> list = menuMapper.queryListByParentId(parentId);

        List<MenuVo> menuVoList = new ArrayList<>();
        for (Menu menu : list) {
            if (menu.getType() != 2) {
                if (menuList == null) {
                    menuVoList.add(getMenuTreeList(menu,null));
                }else if (menuList.contains(menu.getMenuId())) {
                    menuVoList.add(getMenuTreeList(menu,menuList));
                }
            }
        }
        return menuVoList;
    }

    private MenuVo getMenuTreeList(Menu menu,List<Long> menuList) {
        MenuVo menuVo = new MenuVo();
        menuVo.setId(menu.getMenuId());
        menuVo.setPath(menu.getPath());
        menuVo.setName(menu.getName());
        HashMap<String,Object> meta = new HashMap<>();
        if (menu.getHideInMenu() != null) {
            meta.put("hideInMenu", menu.getHideInMenu());
        }
        if (menu.getIcon() != null) {
            meta.put("icon", menu.getIcon());
        }
        meta.put("title",menu.getTitle());
        menuVo.setMeta(meta);
        menuVo.setComponent(menu.getComponent());
        List<MenuVo> childMenu = queryListByParentId(menu.getMenuId(),menuList);
        if (childMenu != null) {
            menuVo.setChildren(childMenu);
        }
        return menuVo;
    }
}
