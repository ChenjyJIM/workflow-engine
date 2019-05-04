package com.graduate.engine.controller;

import com.graduate.engine.model.Role;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.RoleMenuService;
import com.graduate.engine.service.RoleService;
import com.graduate.engine.utils.Constant;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.vaildator.ValidatorUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/role")
@RestController
public class RoleController extends AbstractController {
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;

    @ApiOperation("获取角色列表")
    @GetMapping("/list")
    public ResponseResult getRoleList(){
        Map<String, Object> map = new HashMap<>();

        //除了管理员，只能查询自己创建的角色
        if (getUserId() != Constant.SUPER_ADMIN) {
            map.put("create_user_id",getUserId());
        }
        List<Role> list = roleService.getRoleList(map);
        return ResponseResult.buildSuccess(list);
    }

    @ApiOperation("获取角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long"),
    })
    @GetMapping("/info/{roleId}")
    public ResponseResult getRoleInfo(@PathVariable("roleId") Long roleId){
        Role role = roleService.getById(roleId);
        role.setMenuId(roleMenuService.queryMenuIdList(roleId));
        return ResponseResult.buildSuccess(role);
    }

    @ApiOperation("新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "Role"),
    })
    @PostMapping("/info")
    public ResponseResult saveRoleInfo(@RequestBody Role role) {
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        roleService.saveRole(role);

        return ResponseResult.buildSuccess();
    }

    @ApiOperation("更新角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色", required = true, dataType = "Role"),
    })
    @PutMapping("/info")
    public ResponseResult updateRoleInfo(@RequestBody Role role) {
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        roleService.updateRole(role);

        return ResponseResult.buildSuccess();
    }

    @ApiOperation("删除角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long"),
    })
    @DeleteMapping("/info/{roleId}")
    public ResponseResult deleteRoleInfo(@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);

        return ResponseResult.buildSuccess();
    }
}
