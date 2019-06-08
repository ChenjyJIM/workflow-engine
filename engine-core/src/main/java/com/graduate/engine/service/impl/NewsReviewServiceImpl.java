package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.model.NewsDetails;
import com.graduate.engine.model.NewsEdit;
import com.graduate.engine.model.NewsPublish;
import com.graduate.engine.model.NewsReview;
import com.graduate.engine.mapper.NewsReviewMapper;
import com.graduate.engine.service.*;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 新闻审核表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
@Service
public class NewsReviewServiceImpl extends ServiceImpl<NewsReviewMapper, NewsReview> implements NewsReviewService {
    @Resource
    NewsPublishService publishService;
    @Resource
    NewsEditService newsEditService;
    @Resource
    MessageService messageService;
    @Resource
    NewsDetailsService detailsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reviewNews(Long editId, Long userId) {
        //新增审核记录
        NewsReview newsReview = new NewsReview();
        newsReview.setEditId(editId);
        newsReview.setReviewPersonId(userId);
        newsReview.setReviewTime(DateUtils.getCurrentSecondTimestamp());
        this.save(newsReview);
        //更新新闻编辑表
        NewsEdit newsEdit = newsEditService.getById(editId);
        newsEdit.setStatus(1L);
        newsEditService.updateById(newsEdit);
        //发布新闻
        NewsPublish newsPublish = new NewsPublish();
        newsPublish.setDetailsId(newsEdit.getDetailsId());
        newsPublish.setType(newsEdit.getType());
        newsPublish.setReviewId(newsReview.getReviewId());
        newsPublish.setPublishTime(DateUtils.getCurrentSecondTimestamp());
        publishService.save(newsPublish);
        //发送信息给新闻编辑者
        NewsDetails newsDetails = detailsService.getById(newsEdit.getDetailsId());
        messageService.sendMessageToUsers("新闻发布成功",
                "<div>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>您编辑的新闻：“" + newsDetails.getNewsTitle() + "”通过审核，发布成功！</span></div>",
                new Long[]{newsEdit.getEditPersonId()});
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reviewNews(Long editId, String message, Long userId) {
        //新增审核记录
        NewsReview newsReview = new NewsReview();
        newsReview.setEditId(editId);
        newsReview.setReviewPersonId(userId);
        newsReview.setReviewTime(DateUtils.getCurrentSecondTimestamp());
        newsReview.setReviewMessage(message);
        this.save(newsReview);
        //更新编辑表
        NewsEdit newsEdit = newsEditService.getById(editId);
        newsEdit.setStatus(2L);
        newsEditService.updateById(newsEdit);
        //发送信息给新闻编辑者
        NewsDetails newsDetails = detailsService.getById(newsEdit.getDetailsId());
        messageService.sendMessageToUsers("新闻发布失败",
                "<div>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>您编辑的新闻：“" + newsDetails.getNewsTitle() + "”未通过审核，请根据审核原因修改！</span><br/><span>审核不通过原因：" + message + "</span></div>",
                new Long[]{newsEdit.getEditPersonId()});
    }
}
