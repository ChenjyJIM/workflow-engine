package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.model.Industry;
import com.graduate.engine.model.Institute;
import com.graduate.engine.model.Union;
import com.graduate.engine.model.UnionInstMapper;
import com.graduate.engine.request.UnionRequest;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.IndustryService;
import com.graduate.engine.service.InstituteService;
import com.graduate.engine.service.UnionInstMapperService;
import com.graduate.engine.service.UnionService;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.vaildator.ValidatorUtils;
import com.graduate.engine.vaildator.group.AddGroup;
import com.graduate.engine.vaildator.group.UpdateGroup;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/societies")
@RestController
public class SocietyController {
    @Resource
    private UnionService unionService;
    @Resource
    private IndustryService industryService;
    @Resource
    private InstituteService instituteService;
    @Resource
    private UnionInstMapperService unionInstMapperService;

    @ApiOperation("新增学联体")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "union",value = "学联体信息",required = true,dataType = "Union")
    })
    @PostMapping("/unions")
    public ResponseResult addUnion(@RequestBody Union union) throws ParseException {
        ValidatorUtils.validateEntity(union, AddGroup.class);

        union.setUnionRegisterDate(DateUtils.getTimeStampByUTC(union.getRegisterTime()));
        if (unionService.save(union)) {
            return ResponseResult.buildSuccess("添加成功！");
        }
        return ResponseResult.buildError("添加失败！");
    }

    @ApiOperation("更新学联体")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "union",value = "学联体信息",required = true,dataType = "Union")
    })
    @PutMapping("/unions")
    public ResponseResult updateUnion(@RequestBody Union union) throws ParseException {
        ValidatorUtils.validateEntity(union, UpdateGroup.class);

        union.setUnionRegisterDate(DateUtils.getTimeStampByUTC(union.getRegisterTime()));
        if (unionService.updateById(union)){
            return ResponseResult.buildSuccess("修改成功！");
        }
        return ResponseResult.buildError("修改失败！");
    }

    @ApiOperation("获取学联体信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unionId", value = "学联体id", required = true, dataType = "String"),
    })
    @GetMapping("/unions/{unionId}")
    public ResponseResult unionInfo(@PathVariable String unionId) {
        Union union = unionService.getById(unionId);
        union.setRegisterTime(DateUtils.getDateStrByTimestamp(union.getUnionRegisterDate()));
        return ResponseResult.buildSuccess(union);
    }

    @ApiOperation("获取学联体列表")
    @GetMapping("/unions/list")
    public ResponseResult unionList() {
        List<Union> list = unionService.getUnionList();
        for (Union union : list) {
            union.setRegisterTime(DateUtils.getDateStrByTimestamp(union.getUnionRegisterDate(),"yyyy-MM-dd"));
        }
        return ResponseResult.buildSuccess(list);
    }

    @ApiOperation("停用学联体")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unionId",value = "学联体id",required = true,dataType = "String")
    })
    @DeleteMapping("/unions/{unionId}")
    public ResponseResult stopUnion(@PathVariable String unionId) {
        unionService.stopByPrimaryKey(unionId);
        return ResponseResult.buildSuccess("学联体停用成功！");
    }

    @ApiOperation("获取学联体所含学会列表")
    @GetMapping("/unions/institutes/{unionId}")
    public ResponseResult getInstList(@PathVariable Long unionId) {
        List<Institute> instituteList;
        try {
            instituteList = unionService.getInstList(unionId);
        } catch (RuntimeException e) {
            return ResponseResult.buildError(e.getMessage());
        }
        return ResponseResult.buildSuccess(instituteList);
    }

    @ApiOperation("学联体添加学会列表")
    @GetMapping("/unions/institutes/list/{unionId}")
    public ResponseResult getAllInstList(@PathVariable Long unionId) {
        List<Institute> list = instituteService.getInstitutes();
        JSONArray jsonArray = new JSONArray();
        List<Long> unionInstGroup = new ArrayList<>();
        List<Institute> instituteList;
        try {
            instituteList = unionService.getInstList(unionId);
            list.forEach(item -> {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("instId",item.getInstId());
                jsonObject.put("instName",item.getInstName());
                for (Institute institute : instituteList) {
                    if (item.getInstName().equals(institute.getInstName())){
                        jsonObject.put("isInclude",true);
                        break;
                    } else {
                        jsonObject.put("isInclude",false);
                    }
                }
                jsonArray.add(jsonObject);
            });
            unionInstGroup = instituteList.stream().map(Institute::getInstId).collect(Collectors.toList());
        } catch (RuntimeException e) {
            list.forEach(item -> {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("instId",item.getInstId());
                jsonObject.put("instName",item.getInstName());
                jsonObject.put("isInclude",false);
                jsonArray.add(jsonObject);
            });
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("instList",jsonArray);
        jsonObject.put("unionInstGroup",unionInstGroup);
        return ResponseResult.buildSuccess(jsonObject);
    }

    @ApiOperation("学联体添加学会")
    @PostMapping("/unions/institutes")
    public ResponseResult instAddInUnion(@RequestBody UnionRequest unionInst) {
        unionInstMapperService.saveOrUpdate(unionInst.getUnionId(),unionInst.getInitId());
        return ResponseResult.buildSuccess("添加成功");
    }

    @ApiOperation("学联体移除学会")
    @DeleteMapping("/unions/institutes/{unionId}/{instId}")
    public ResponseResult instRemoveUnion(@PathVariable Long unionId,@PathVariable Long instId) {
        unionInstMapperService.stopByUnionIdInstId(unionId,instId);
        return ResponseResult.buildSuccess("移除成功");
    }

    @ApiOperation("新增学会行业分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "industry",value = "学会行业信息",required = true,dataType = "Industry")
    })
    @PostMapping("/industries")
    public ResponseResult addIndustry(@RequestBody Industry industry){
        ValidatorUtils.validateEntity(industry,AddGroup.class);

        if (industryService.save(industry)) {
            return ResponseResult.buildSuccess("添加成功！");
        }
        return ResponseResult.buildError("添加失败！");
    }

    @ApiOperation("更新学会行业分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "industry",value = "学会行业信息",required = true,dataType = "Industry")
    })
    @PutMapping("/industries")
    public ResponseResult updateIndustry(@RequestBody Industry industry){
        ValidatorUtils.validateEntity(industry,UpdateGroup.class);

        if (industryService.updateById(industry)) {
            return ResponseResult.buildSuccess("更新成功！");
        }
        return ResponseResult.buildError("更新失败！");
    }

    @ApiOperation("停用学会行业分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "industryId",value = "学会行业代号",required = true,dataType = "String")
    })
    @DeleteMapping("/industries/{industryId}")
    public ResponseResult updateIndustry(@PathVariable String industryId){
        industryService.stopByPrimaryKey(industryId);
        return ResponseResult.buildSuccess("学会行业停用成功！");
    }

    @ApiOperation("获取学会行业分类")
    @GetMapping("/industries")
    public ResponseResult industryList() {
        List<Industry> list = industryService.getIndustryWithoutStop();
        return ResponseResult.buildSuccess(list);
    }

    @ApiOperation("新增学会")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "institute",value = "学会信息",required = true,dataType = "Institute")
    })
    @PostMapping("/institutes")
    public ResponseResult addInstitute(@RequestBody Institute institute) {
        ValidatorUtils.validateEntity(institute,AddGroup.class);

        if (instituteService.save(institute)) {
            return ResponseResult.buildSuccess("添加成功！");
        }
        return ResponseResult.buildError("添加失败！");
    }

    @ApiOperation("更新学会")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "institute",value = "学会信息",required = true,dataType = "Institute")
    })
    @PutMapping("/institutes")
    public ResponseResult updateInstitute(@RequestBody Institute institute) {
        ValidatorUtils.validateEntity(institute,UpdateGroup.class);

        if (instituteService.updateById(institute)) {
            return ResponseResult.buildSuccess("更新成功！");
        }
        return ResponseResult.buildError("更新失败！");
    }

    @ApiOperation("获取学会列表")
    @GetMapping("/institutes/list")
    public ResponseResult instituteList() {
        List<Institute> list = instituteService.getInstitutes();
        JSONArray jsonArray = new JSONArray();
        for (Institute institute : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("instId",institute.getInstId().toString());
            jsonObject.put("instName",institute.getInstName());
            jsonArray.add(jsonObject);
        }
        return ResponseResult.buildSuccess(jsonArray);
    }

    @ApiOperation("获取所有学会信息")
    @GetMapping("/institutes")
    public ResponseResult getInstitute() {
        List<Institute> list = instituteService.getInstitutes();
        return ResponseResult.buildSuccess(list);
    }

    @ApiOperation("获取学会信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instId",value = "学会id",required = true,dataType = "Long")
    })
    @GetMapping("/institutes/{instId}")
    public ResponseResult instituteInfo(@PathVariable Long instId) {
        Institute institute = instituteService.getInstById(instId);
        return ResponseResult.buildSuccess(institute);
    }

    @ApiOperation("停用学会")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instId",value = "学会id",required = true,dataType = "String")
    })
    @DeleteMapping("/institutes/{instId}")
    public ResponseResult stopInstitute(@PathVariable String instId) {
        instituteService.stopByPrimaryKey(instId);
        return ResponseResult.buildSuccess("学会停用成功！");
    }

}
