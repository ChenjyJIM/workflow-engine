package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.TestMapper;
import com.graduate.engine.model.dataobject.PersonMember;
import com.graduate.engine.model.viewobject.PersonMemberVo;
import com.graduate.engine.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jimmy
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private TestMapper testMapper;

    public PersonMemberVo getByPersonId(Long personId) {
        PersonMemberVo personMemberVo = new PersonMemberVo();
        PersonMember personMember = testMapper.getMemberById(personId);
        if (personMember != null) {
            BeanUtils.copyProperties(personMember, personMemberVo);
        } else {
            return null;
        }
        return personMemberVo;
    }
}
