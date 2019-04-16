package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.MessageMapper;
import com.graduate.engine.model.Message;
import com.graduate.engine.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Resource
    MessageMapper messageMapper;

    @Override
    public String getContentById(Long messageId) {
        return messageMapper.queryByMessageId(messageId).getMessageContent();
    }
}
