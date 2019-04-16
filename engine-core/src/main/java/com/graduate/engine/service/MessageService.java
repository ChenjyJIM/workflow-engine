package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.Message;

public interface MessageService extends IService<Message> {

    /**
     * 根据ID获取消息内容
     */
    String getContentById(Long messageId);
}
