package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.model.NewsDetails;
import com.graduate.engine.mapper.NewsDetailsMapper;
import com.graduate.engine.service.NewsDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 新闻内容表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
@Service
public class NewsDetailsServiceImpl extends ServiceImpl<NewsDetailsMapper, NewsDetails> implements NewsDetailsService {
    @Resource
    NewsDetailsMapper newsDetailsMapper;

    @Override
    public List<Long> likeSelectByTitle(String title) {
        return newsDetailsMapper.likeSelectByTitle(title);
    }
}
