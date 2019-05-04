package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Message;

public interface MessageService extends IService<Message> {

    /**
     * 根据ID获取消息内容
     */
    String getContentById(Long messageId);

    /**
     * 保存信息
     * @return Long messageId 此条信息的ID
     */
    Long saveMessage(String messageTitle,String messageContent);

    /**
     * 给所有用户发送系统信息
     */
    void sendMessageToAll(String messageTitle,String messageContent);

    /**
     * 按角色发送信息
     */
    void sendMessageToRoles(String messageTitle,String messageContent,Long[] roleIds);

    /**
     * 按用户发送信息
     */
    void sendMessageToUsers(String messageTitle,String messageContent,Long[] userIds);
}
