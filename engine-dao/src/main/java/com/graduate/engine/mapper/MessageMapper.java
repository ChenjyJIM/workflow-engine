package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.Message;


public interface MessageMapper extends BaseMapper<Message> {

    Message queryByMessageId(Long messageId);

}
