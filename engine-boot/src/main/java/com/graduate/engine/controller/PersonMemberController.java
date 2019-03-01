package com.graduate.engine.controller;

import com.graduate.engine.annotation.LoginRequired;
import com.graduate.engine.model.viewobject.PersonMemberVo;
import com.graduate.engine.request.TestQuery;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.BaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author jimmy
 */
@RequestMapping("/member")
@RestController
public class PersonMemberController {

    @Resource
    private BaseService baseService;

    @PostMapping("/getById")
    @LoginRequired
    public ResponseResult getById(@RequestBody TestQuery testQuery) {
        try {
            if (testQuery != null) {
                PersonMemberVo personMemberVo = baseService.getByPersonId(1L);

                return ResponseResult.buildSuccess(personMemberVo);
            }else {
                return ResponseResult.buildError("参数错误！");
            }
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }

    }
}
