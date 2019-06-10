package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.NewsPublishMapper;
import com.graduate.engine.model.NewsEdit;
import com.graduate.engine.model.NewsPublish;
import com.graduate.engine.service.NewsDetailsService;
import com.graduate.engine.service.NewsEditService;
import com.graduate.engine.service.NewsPublishService;
import com.graduate.engine.utils.PageUtils;
import com.graduate.engine.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 新闻发布表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
@Service
public class NewsPublishServiceImpl extends ServiceImpl<NewsPublishMapper, NewsPublish> implements NewsPublishService {
    @Resource
    NewsEditService newsEditService;
    @Resource
    NewsDetailsService newsDetailsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        boolean withoutStop = Boolean.parseBoolean((String) params.get("withoutStop"));
        String type = (String) params.get("type");
        String newsTitle = (String) params.get("newsTitle");
        List<Long> newsDetailsIds = new ArrayList<>();
        if (StringUtils.isNotBlank(newsTitle)) {
            newsDetailsIds = newsDetailsService.likeSelectByTitle(newsTitle);
        }

        IPage<NewsPublish> page = this.page(
                new Query<NewsPublish>().getPage(params, "publish_time", false),
                new QueryWrapper<NewsPublish>()
                        .eq(withoutStop, "stop", false)
                        .eq(StringUtils.isNotBlank(type), "type", type)
                        .in(StringUtils.isNotBlank(newsTitle), "details_id", newsDetailsIds)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopPublishNews(Long publishId) {
        //停用发布新闻
        NewsPublish publish = this.getById(publishId);
        publish.setStop(true);
        this.updateById(publish);
        //更新新闻编辑表的新闻状态
        NewsEdit newsEdit = newsEditService.selectEditByDetailsId(publish.getDetailsId());
        newsEdit.setStatus(3L);
        newsEditService.updateById(newsEdit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void republishNews(Long publishId) {
        //恢复发布新闻
        NewsPublish publish = this.getById(publishId);
        publish.setStop(false);
        this.updateById(publish);
        //更新新闻编辑表的新闻状态
        NewsEdit newsEdit = newsEditService.selectEditByDetailsId(publish.getDetailsId());
        newsEdit.setStatus(1L);
        newsEditService.updateById(newsEdit);
    }
}
