package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.NewsDetails;

import java.util.List;

/**
 * <p>
 * 新闻内容表 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
public interface NewsDetailsService extends IService<NewsDetails> {
    List<Long> likeSelectByTitle(String title);
}
