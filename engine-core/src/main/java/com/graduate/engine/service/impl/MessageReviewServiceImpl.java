package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.MessageReviewMapper;
import com.graduate.engine.model.MessageReview;
import com.graduate.engine.service.MessageReviewService;
import org.springframework.stereotype.Service;

@Service
public class MessageReviewServiceImpl extends ServiceImpl<MessageReviewMapper, MessageReview> implements MessageReviewService {

}
