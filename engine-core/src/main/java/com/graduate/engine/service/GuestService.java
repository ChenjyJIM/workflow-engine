package com.graduate.engine.service;

import com.graduate.engine.model.viewobject.GuestVo;
import com.graduate.engine.request.MemberModifiedRequest;

public interface GuestService {
    GuestVo getByGuestId(Long guestId);

    int infoModified(MemberModifiedRequest memberModifiedRequest);
}
