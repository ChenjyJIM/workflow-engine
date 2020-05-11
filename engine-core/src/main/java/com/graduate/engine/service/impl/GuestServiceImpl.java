package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.GuestMapper;
import com.graduate.engine.model.Guest;
import com.graduate.engine.model.viewobject.GuestVo;
import com.graduate.engine.request.MemberModifiedRequest;
import com.graduate.engine.service.GuestService;
import com.graduate.engine.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lianglili
 */
@Service
public class GuestServiceImpl implements GuestService {
    @Resource
    private GuestMapper guestMapper;

    @Override
    public GuestVo getByGuestId(Long guestId) {
        Guest guest = guestMapper.selectByPrimaryKey(guestId);
        GuestVo guestVo = BeanUtils.copyBean(guest, GuestVo.class);
        return guestVo;
    }

    @Override
    public int infoModified(MemberModifiedRequest memberModifiedRequest) {
        Guest guest = BeanUtils.copyBean(memberModifiedRequest, Guest.class);
        return guestMapper.updateByPrimaryKeySelective(guest);
    }
}
