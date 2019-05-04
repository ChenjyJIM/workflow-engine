package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.MessageMapper;
import com.graduate.engine.model.Login;
import com.graduate.engine.model.Message;
import com.graduate.engine.service.MessageService;
import com.graduate.engine.service.PersonMessageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Resource
    MessageMapper messageMapper;
    @Resource
    PersonMessageService personMessageService;

    @Override
    public String getContentById(Long messageId) {
        return messageMapper.queryByMessageId(messageId).getMessageContent();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveMessage(String messageTitle, String messageContent) {
        Message message = new Message();
        message.setMessageTitle(messageTitle);
        message.setMessageContent(messageContent);
        message.setCreateTime(new Date());
        message.setCreateUserId(getCreateUserId());
        this.save(message);
        return message.getMessageId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendMessageToAll(String messageTitle, String messageContent) {
        Long messageId = this.saveMessage(messageTitle,messageContent);
        personMessageService.messageToAll(messageId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendMessageToRoles(String messageTitle, String messageContent, Long[] roleIds) {
        Long messageId = this.saveMessage(messageTitle,messageContent);
        personMessageService.messageToRoles(messageId,roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendMessageToUsers(String messageTitle, String messageContent, Long[] userIds) {
        Long messageId = this.saveMessage(messageTitle,messageContent);
        personMessageService.messageToUsers(messageId,userIds);
    }

    private Long getCreateUserId() {
        Login login = (Login) SecurityUtils.getSubject().getPrincipal();
        return login.getLoginId();
    }
}
