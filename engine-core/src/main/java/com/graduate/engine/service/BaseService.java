package com.graduate.engine.service;

import com.graduate.engine.model.viewobject.PersonMemberVo;

public interface BaseService {
    PersonMemberVo getByPersonId(Long personId);
}
