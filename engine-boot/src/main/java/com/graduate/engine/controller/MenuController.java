package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.exception.BasicException;
import com.graduate.engine.model.Menu;
import com.graduate.engine.model.viewobject.MenuVo;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.MenuService;
import com.graduate.engine.service.ShiroService;
import com.graduate.engine.utils.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RequestMapping("/menu")
@RestController
public class MenuController extends AbstractController {
    @Resource
    private MenuService menuService;
    @Resource
    private ShiroService shiroService;

    @ApiOperation("获取用户导航菜单")
    @GetMapping("/nav")
    public ResponseResult getMenu() {
        List<MenuVo> menuList = menuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menuList", menuList);
        jsonObject.put("permissions", permissions);
        return ResponseResult.buildSuccess(jsonObject);
    }

    @ApiOperation("获取菜单列表")
    @GetMapping("/list")
    public ResponseResult getMenuList() {
        List<Menu> menuVoList = menuService.list();
        for (Menu menu : menuVoList) {
            Menu parentMenu = menuService.getById(menu.getParentId());
            if (parentMenu != null) {
                menu.setParentName(parentMenu.getTitle());
            }
        }
        return ResponseResult.buildSuccess(menuVoList);
    }

    @ApiOperation("获取菜单下拉列表")
    @GetMapping("/select")
    public ResponseResult getMenuSelectList() {
        List<MenuVo> menuList = menuService.getUserMenuList(getUserId());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("menuId", 0);
        jsonObject1.put("menuName", "一级菜单");
        jsonArray.add(jsonObject1);
        return ResponseResult.buildSuccess(getSelect(menuList, jsonArray));
    }

    private JSONArray getSelect(List<MenuVo> menuList, JSONArray select) {
        for (MenuVo menu : menuList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("menuId", menu.getId());
            jsonObject.put("menuName", menu.getMeta().get("title"));
            select.add(jsonObject);
            if (menu.getChildren() != null && menu.getChildren().size() != 0) {
                select = getSelect(menu.getChildren(), select);
            }
        }
        return select;
    }

    @ApiOperation("获取菜单树状列表")
    @GetMapping("/tree")
    public ResponseResult getMenuTree() {
        List<MenuVo> menuList = menuService.getUserMenuTree(getUserId());
        return ResponseResult.buildSuccess(getTreeChildren(menuList));
    }

    private JSONArray getTreeChildren(List<MenuVo> menuList) {
        JSONArray children = new JSONArray();
        for (MenuVo menu : menuList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", menu.getId());
            jsonObject.put("title", menu.getMeta().get("title"));
            if (menu.getChildren() != null && menu.getChildren().size() != 0) {
                jsonObject.put("children", getTreeChildren(menu.getChildren()));
            }
            children.add(jsonObject);
        }
        return children;
    }

    @ApiOperation("获取菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "菜单id", required = true, dataType = "Long"),
    })
    @GetMapping("/info/{menuId}")
    public ResponseResult getInfo(@PathVariable("menuId") Long menuId) {
        Menu menu = menuService.getById(menuId);
        Menu parentMenu = menuService.getById(menu.getParentId());
        if (parentMenu != null) {
            menu.setParentName(parentMenu.getTitle());
        }
        return ResponseResult.buildSuccess(menu);
    }

    @ApiOperation("新增菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu", value = "菜单信息", required = true, dataType = "Menu")
    })
    @PostMapping("/info")
    public ResponseResult saveMenu(@RequestBody Menu menu) {
        verifyForm(menu);
        menuService.save(menu);
        return ResponseResult.buildSuccess();
    }

    @ApiOperation("更新菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu", value = "菜单信息", required = true, dataType = "Menu")
    })
    @PutMapping("/info")
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        verifyForm(menu);
        menuService.updateById(menu);
        return ResponseResult.buildSuccess();
    }

    @ApiOperation("删除菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "菜单id", required = true, dataType = "Long"),
    })
    @DeleteMapping("/info/{menuId}")
    public ResponseResult deleteMenu(@PathVariable("menuId") Long menuId) {
        if (menuId == 0) {
            throw new BasicException("Id 错误，不能为一级菜单");
        }

        List<Menu> menuList = menuService.queryListByParentId(menuId);
        if (menuList.size() > 0) {
            return ResponseResult.buildError("请先删除子菜单或按钮");
        }
        menuService.delete(menuId);
        return ResponseResult.buildSuccess();
    }

    private void verifyForm(Menu menu) {
        if (StringUtils.isBlank(menu.getTitle())) {
            throw new BasicException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new BasicException("父菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getPath())) {
                throw new BasicException("菜单URL不能为空");
            }
            if (StringUtils.isBlank(menu.getName())) {
                throw new BasicException("缓存名称不能为空");
            }
            if (StringUtils.isBlank(menu.getComponent())) {
                throw new BasicException("页面路径不能为空");
            }
        }
    }
}
