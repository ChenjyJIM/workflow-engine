package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.PersonMessage;
import com.graduate.engine.model.viewobject.MessageSummary;

import java.util.List;

public interface PersonMessageService extends IService<PersonMessage> {

    /**
     * 获取用户未读消息数量
     */
    int getUnreadCountByUserId(Long userId);

    /**
     * 获取用户未读信息列表
     */
    List<MessageSummary> getUnreadList(Long userId);

    /**
     * 获取用户已读信息列表
     */
    List<MessageSummary> getReadList(Long userId);

    /**
     * 获取用户回收站信息列表
     */
    List<MessageSummary> getTrashList(Long userId);

    /**
     * 将信息标记为已读
     */
    void hasReadMessage(Long userId,Long messageId);

    /**
     * 将消息标记为删除
     */
    void removeMessage(Long userId,Long messageId);

    /**
     * 将消息还原为已读
     */
    void restoreMessage(Long userId,Long messageId);

    /**
     * 将消息发送给所有用户
     */
    int messageToAll(Long messageId);

    /**
     * 将消息发送给某些角色
     */
    int messageToRoles(Long messageId,Long[] roleIds);

    /**
     * 将消息发送给某些人
     */
    int messageToUsers(Long messageId,Long[] userIds);
}
