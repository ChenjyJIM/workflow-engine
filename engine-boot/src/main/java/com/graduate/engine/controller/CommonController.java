package com.graduate.engine.controller;

import com.graduate.engine.mapper.*;
import com.graduate.engine.model.InstituteSub;
import com.graduate.engine.model.viewobject.InstInfoSimple;
import com.graduate.engine.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 常用查询接口
 * 用于前端select枚举接受的返回数据
 */

@RestController
@RequestMapping("/common")
public class CommonController {

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
    public ResponseResult getInstByPersonId(Long personId) {
        try {
            List<InstInfoSimple> instSimples = new ArrayList<>();
            instituteMapper.getByPersonId(personId).forEach( institute -> {
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
}

