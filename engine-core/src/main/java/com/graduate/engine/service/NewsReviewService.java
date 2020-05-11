package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.NewsReview;

/**
 * <p>
 * 新闻审核表 服务类
 * </p>
 *
 * @author lianglili
 * @since 2019-05-24
 */
public interface NewsReviewService extends IService<NewsReview> {
    //新闻审核通过
    void reviewNews(Long editId, Long userId);

    //新闻审核不通过
    void reviewNews(Long editId, String message, Long userId);
}
