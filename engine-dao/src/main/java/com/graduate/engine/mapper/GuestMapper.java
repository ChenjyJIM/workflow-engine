package com.graduate.engine.mapper;

import com.graduate.engine.model.Guest;

public interface GuestMapper {
    int deleteByPrimaryKey(Integer guestId);

    int insert(Guest record);

    int insertSelective(Guest record);

    Guest selectByPrimaryKey(Integer guestId);

    int updateByPrimaryKeySelective(Guest record);

    int updateByPrimaryKey(Guest record);

    Guest selectByGuestName(String guestName);
}
