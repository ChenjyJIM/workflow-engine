package com.graduate.engine.service;

import com.graduate.engine.model.viewobject.PersonMemberVo;
import com.graduate.engine.request.MemberModifiedRequest;

public interface PersonMemberService {

    PersonMemberVo getByPersonId(Long personId);

    int infoModified(MemberModifiedRequest memberModifiedRequest);

}
