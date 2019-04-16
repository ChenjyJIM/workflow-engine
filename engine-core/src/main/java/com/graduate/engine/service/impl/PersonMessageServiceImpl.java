package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.MessageMapper;
import com.graduate.engine.mapper.PersonMessageMapper;
import com.graduate.engine.model.Message;
import com.graduate.engine.model.PersonMessage;
import com.graduate.engine.model.viewobject.MessageSummary;
import com.graduate.engine.service.BaseService;
import com.graduate.engine.service.PersonMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PersonMessageServiceImpl extends ServiceImpl<PersonMessageMapper, PersonMessage> implements PersonMessageService {
    @Resource
    PersonMessageMapper personMessageMapper;
    @Resource
    MessageMapper messageMapper;
    @Resource
    BaseService baseService;

    @Override
    public int getUnreadCountByUserId(Long userId) {
        return personMessageMapper.getUnreadContByUserId(userId);
    }

    @Override
    public List<MessageSummary> getUnreadList(Long userId) {
        List<Long> unreadList = personMessageMapper.getUnreadList(userId);
        List<Message> unreadMessageList = unreadList.stream().map(u -> messageMapper.queryByMessageId(u)).collect(toList());
        List<MessageSummary> messageSummaryList = new ArrayList<>();
        for (Message unreadMessage : unreadMessageList) {
            messageSummaryList.add(formatMessage(unreadMessage));
        }
        return messageSummaryList;
    }

    @Override
    public List<MessageSummary> getReadList(Long userId) {
        List<Long> readList = personMessageMapper.getReadList(userId);
        List<Message> readMessageList = readList.stream().map(r -> messageMapper.queryByMessageId(r)).collect(toList());
        List<MessageSummary> messageSummaryList = new ArrayList<>();
        for (Message readMessage : readMessageList) {
            messageSummaryList.add(formatMessage(readMessage));
        }
        return messageSummaryList;
    }

    @Override
    public List<MessageSummary> getTrashList(Long userId) {
        List<Long> trashList = personMessageMapper.getTrashList(userId);
        List<Message> trashMessageList = trashList.stream().map(t -> messageMapper.queryByMessageId(t)).collect(toList());
        List<MessageSummary> messageSummaryList = new ArrayList<>();
        for (Message trashMessage : trashMessageList) {
            messageSummaryList.add(formatMessage(trashMessage));
        }
        return messageSummaryList;
    }

    @Override
    public void hasReadMessage(Long userId,Long messageId) {
        personMessageMapper.hasReadMsg(userId,messageId);
    }

    @Override
    public void removeMessage(Long userId,Long messageId) {
        personMessageMapper.removeMsg(userId,messageId);
    }

    @Override
    public void restoreMessage(Long userId,Long messageId) {
        personMessageMapper.hasReadMsg(userId,messageId);
    }

    /**
     * 将Message转换成MessageSummary
     */
    private MessageSummary formatMessage(Message message) {
        MessageSummary messageSummary = new MessageSummary();
        messageSummary.setMessageId(message.getMessageId());
        messageSummary.setMessageTitle(message.getMessageTitle());
        messageSummary.setCreateTime(message.getCreateTime());
        messageSummary.setCreateUserId(message.getCreateUserId());
        messageSummary.setCreateUserName(baseService.getByPersonId(message.getCreateUserId()).getName());
        return messageSummary;
    }
}
