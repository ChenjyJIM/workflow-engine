package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graduate.engine.annotation.CurrentUser;
import com.graduate.engine.annotation.LoginRequired;
import com.graduate.engine.mapper.GarbageDetailMapper;
import com.graduate.engine.mapper.InstituteMapper;
import com.graduate.engine.mapper.PersonRoleMapperMapper;
import com.graduate.engine.mapper.RoleMapper;
import com.graduate.engine.model.*;
import com.graduate.engine.model.viewobject.UserInfoVo;
import com.graduate.engine.model.viewobject.UserVo;
import com.graduate.engine.request.RegisterRequest;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.*;
import com.graduate.engine.utils.Constant;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.utils.PageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lianglili
 */
@RequestMapping("/authentication")
@RestController
public class LoginController extends AbstractController {
    @Resource
    private LoginService loginService;
    @Resource
    private ShiroService shiroService;
    @Resource
    private PersonRoleMapperService personRoleMapperService;
    @Resource
    private PersonRoleMapperMapper personRoleMapperMapper;
    @Resource
    private RoleMapper roleMapper;

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody Login login) {
        // TODO 访客登录和用户登录判断，访客更新登录时间。
        try {
            if (login == null) {
                ResponseResult.buildError("入参错误，请检查！");
            }
            String name = login.getLoginName();
            Login userInDataBase = loginService.findByName(name);
            if (userInDataBase == null) {
                return ResponseResult.buildError("用户不存在！");
            }
            if (!loginService.comparePassword(login, userInDataBase)) {
                return ResponseResult.buildError("密码错误！");
            }
            if (userInDataBase.getGuestId() != null) {
                // 更新访客的最后登录时间
                System.out.println(loginService.updateLastLogin(userInDataBase.getGuestId()));
            }
            String token = loginService.getToken(userInDataBase);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            if (userInDataBase.getGuestId() == null && userInDataBase.getPersonId() == null) {
                //其他登录类型
                jsonObject.put("type", 0);
                return ResponseResult.buildSuccess(jsonObject);
            }
            jsonObject.put("type", userInDataBase.getPersonId() != null ? 1 : 2);
            return ResponseResult.buildSuccess(jsonObject);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    /**
     * 用户注册临时账户
     */
    @PostMapping("/guest/register")
    public ResponseResult register(@RequestBody RegisterRequest request) {
        try {
            if (request == null || request.getLoginName() == null) {
                return ResponseResult.buildError("参数错误！");
            }
            if (loginService.findByName(request.getLoginName()) != null) {
                return ResponseResult.buildError("账号已存在！");
            }
            if (1 == loginService.addGuest(request)) {
                //给账户添加临时用户角色
                personRoleMapperService.addGuestRole(loginService.findByName(request.getLoginName()).getLoginId());
                return ResponseResult.buildSuccess("注册成功！");
            } else {
                return ResponseResult.buildError("注册失败！");
            }
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/test")
    @LoginRequired
    public Object testCurrentUser(@CurrentUser Login login) {
        return login;
    }

    @ApiOperation("获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
    })
    @GetMapping("/info")
    public JSONObject getUserInfo(@RequestParam(value = "token") String token) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getUser().getLoginName());
        jsonObject.put("user_id", getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        jsonObject.put("access", permissions);
        jsonObject.put("token", token);
        jsonObject.put("personId", getPersonId());
        jsonObject.put("avator", "https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png");
        return jsonObject;
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/user/list")
    public ResponseResult getUserList(@RequestParam Map<String, Object> params) {
        PageUtils page = loginService.queryPage(params);
        List<UserVo> userList = new ArrayList<>();
        List<Login> list = (List<Login>) page.getList();
        for (Login login : list) {
            UserVo userVo = new UserVo();
            userVo.setLoginId(login.getLoginId());
            userVo.setLoginName(login.getLoginName());
            userVo.setStop(login.getStop() ? 1L : 0L);

            if (login.getGuestId() != null) {
                userVo.setType(2L);
            } else if (login.getPersonId() != null) {
                userVo.setType(1L);
            } else {
                userVo.setType(0L);
            }
            if (login.getPersonId() == null) {
                userVo.setRoleName("普通用户");
            } else {
                PersonRoleMapper personRoleMapper = personRoleMapperMapper.selectByPersonId(login.getLoginId());
                if (personRoleMapper != null) {
                    LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(Role::getRoleId, personRoleMapper.getRoleId());
                    Role role = roleMapper.selectOne(queryWrapper);
                    userVo.setRoleName(role.getRoleName());
                } else {
                    userVo.setRoleName("普通用户");
                }
            }
            if (login.getLoginId().equals(1L)) {
                userVo.setRoleName("系统管理员");
            }
            userList.add(userVo);
        }
        page.setList(userList);
        return ResponseResult.buildSuccess(page);
    }

    @ApiOperation("批量停用用户")
    @DeleteMapping("/user")
    public ResponseResult delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return ResponseResult.buildError("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return ResponseResult.buildError("当前用户不能删除");
        }

        loginService.deleteBatch(userIds);

        return ResponseResult.buildSuccess();
    }

    @ApiOperation("管理员新增用户")
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody UserInfoVo userInfoVo) {
        try {
            if (userInfoVo == null || userInfoVo.getLoginName() == null || userInfoVo.getRoleId() == null) {
                return ResponseResult.buildError("参数错误！");
            }
            if (loginService.findByName(userInfoVo.getLoginName()) != null) {
                return ResponseResult.buildError("账号已存在！");
            }
            //向Login表和PersonMember表中插入数据
            Login login = loginService.addUser(userInfoVo);
            //给新建的用户赋予权限
            personRoleMapperService.addUserRole(login.getLoginId(), userInfoVo.getRoleId());
            return ResponseResult.buildSuccess("新增成功！");
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @ApiOperation("管理员获取用户信息")
    @GetMapping("/user/{userId}")
    public ResponseResult getUserInfo(@PathVariable("userId") Long userId) {
        UserInfoVo userInfoVo = loginService.getUser(userId);
        PersonRoleMapper personRoleMapper = personRoleMapperService.selectByPersonId(userId);
        if (personRoleMapper != null) {
            userInfoVo.setRoleId(personRoleMapper.getRoleId());
        }
        return ResponseResult.buildSuccess(userInfoVo);
    }

    @ApiOperation("管理员更新用户")
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody UserInfoVo userInfoVo) {
        try {
            loginService.updateUser(userInfoVo);
            personRoleMapperService.updateUserRole(userInfoVo.getId(), userInfoVo.getRoleId());
            return ResponseResult.buildSuccess("新增成功！");
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }
}
