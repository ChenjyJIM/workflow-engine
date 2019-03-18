package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.PersonMemberMapper;
import com.graduate.engine.model.PersonMember;
import com.graduate.engine.model.viewobject.PersonMemberVo;
import com.graduate.engine.request.MemberModifiedRequest;
import com.graduate.engine.service.PersonMemberService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * @author jimmy
 */
@Service
public class PersonMemberServiceImpl implements PersonMemberService {

    @Resource
    private PersonMemberMapper personMemberMapper;

    public PersonMemberVo getByPersonId(Long personId) {
       PersonMember personMember = personMemberMapper.selectByPersonMemberID(personId);
        PersonMemberVo personMemberVo = BeanUtils.copyBean(personMember, PersonMemberVo.class);
        return personMemberVo;
    }

    public int infoModified(MemberModifiedRequest memberModifiedRequest) {
        PersonMember personMember = BeanUtils.copyBean(memberModifiedRequest, PersonMember.class);
        if(memberModifiedRequest.getBirthday() != null){
            try {
                personMember.setBirthday(DateUtils.getTimeStampByUTC(memberModifiedRequest.getBirthday()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return personMemberMapper.updateByPrimaryKeySelective(personMember);
    }

}
