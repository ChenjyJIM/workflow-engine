package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.PersonMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMessageMapper extends BaseMapper<PersonMessage> {

    int getUnreadContByUserId(Long userId);

    List<Long> getUnreadList(Long userId);

    List<Long> getReadList(Long userId);

    List<Long> getTrashList(Long userId);

    void hasReadMsg(@Param("userId") Long userId,@Param("messageId") Long messageId);

    void removeMsg(@Param("userId")Long userId,@Param("messageId")Long messageId);

}
