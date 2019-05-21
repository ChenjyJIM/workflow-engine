package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.mapper.*;
import com.graduate.engine.model.Institute;
import com.graduate.engine.model.InstituteSub;
import com.graduate.engine.model.Role;
import com.graduate.engine.model.viewobject.InstInfoSimple;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.InstituteService;
import com.graduate.engine.service.RoleService;
import com.graduate.engine.utils.Constant;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * 常用查询接口
 * 用于前端select枚举接受的返回数据
 */

@RestController
@RequestMapping("/common")
public class CommonController extends AbstractController {

    @Resource
    private PersonMemberMapper personMemberMapper;

    @Resource
    private IndustryMapper industryMapper;

    @Resource
    private EducationMapper educationMapper;

    @Resource
    private InstituteMapper instituteMapper;

    @Resource
    private InstituteSubMapper instituteSubMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private InstituteService instituteService;

    /**
     * 查询学会所有成员接口
     * @param instId 为空时查询所有成员
     */
    @GetMapping("/getMember")
    public ResponseResult getMember(Long instId) {
        try {
            return ResponseResult.buildSuccess(personMemberMapper.getByInstId(instId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    /**
     * 根据personId查询该用户参与的学会以及该学会所有分会
     *
     * @return
     */
    @GetMapping("/getInstByPersonId")
    public ResponseResult getInstByPersonId() {
        try {
            List<InstInfoSimple> instSimples = new ArrayList<>();
            instituteMapper.getByPersonId(getPersonId()).forEach( institute -> {
                InstInfoSimple instInfoSimple = new InstInfoSimple();
                instInfoSimple.setInstName(institute.getInstName());
                instInfoSimple.setInstId(institute.getInstId());
                instInfoSimple.setSubInfoSimpleList(instituteSubMapper.getInstSubsByInstId(institute.getInstId()).stream()
                        .map(InstituteSub::packetSimpleInfo)
                        .collect(toList()));
                instSimples.add(instInfoSimple);
            });
            return ResponseResult.buildSuccess(instSimples);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");

        }
    }


    /**
     * 查询所有行业分类接口
     */
    @GetMapping("/getIndustryName")
    public ResponseResult getIndustryName() {
        try {
            return ResponseResult.buildSuccess(industryMapper.getAll());
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    /**
     * 查询所有学历接口
     * @return
     */
    @GetMapping("/getAllEducation")
    public ResponseResult getAllEducation() {
        try {
            return ResponseResult.buildSuccess(educationMapper.getAll());
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }

    }

    @ApiOperation("获取学会列表")
    @GetMapping("/institutes")
    public ResponseResult instituteList() {
        List<Institute> list = instituteService.getInstitutes();
        JSONArray jsonArray = new JSONArray();
        for (Institute institute : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("instId",institute.getInstId());
            jsonObject.put("instName",institute.getInstName());
            jsonArray.add(jsonObject);
        }
        return ResponseResult.buildSuccess(jsonArray);
    }

    /**
     * 查询角色接口
     */
    @GetMapping("/getAllRole")
    public ResponseResult getAllRole() {
        try {
            Map<String, Object> map = new HashMap<>();

            //除了管理员，只能查询自己创建的角色
            if (getUserId() != Constant.SUPER_ADMIN) {
                map.put("create_user_id",getUserId());
            }
            List<Role> list = roleService.getRoleList(map);
            JSONArray jsonArray = new JSONArray();
            for (Role role : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("roleId",role.getRoleId());
                jsonObject.put("roleName",role.getRoleName());
                jsonArray.add(jsonObject);
            }
            return ResponseResult.buildSuccess(jsonArray);
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }
}

