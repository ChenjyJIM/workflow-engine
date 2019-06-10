package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.NewsEditMapper;
import com.graduate.engine.model.NewsCategory;
import com.graduate.engine.model.NewsDetails;
import com.graduate.engine.model.NewsEdit;
import com.graduate.engine.service.NewsCategoryService;
import com.graduate.engine.service.NewsDetailsService;
import com.graduate.engine.service.NewsEditService;
import com.graduate.engine.utils.DateUtils;
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
 * 新闻编辑表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
@Service
public class NewsEditServiceImpl extends ServiceImpl<NewsEditMapper, NewsEdit> implements NewsEditService {
    @Resource
    NewsDetailsService newsDetailsService;
    @Resource
    NewsCategoryService categoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editNews(NewsEdit newsEdit, NewsDetails newsDetails) {
        newsDetailsService.save(newsDetails);
        newsEdit.setDetailsId(newsDetails.getDetailsId());
        this.save(newsEdit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNews(NewsEdit newsEdit, NewsDetails newsDetails) {
        newsDetailsService.updateById(newsDetails);
        newsEdit.setStatus(0L);
        this.updateById(newsEdit);
    }

    @Override
    public NewsEdit selectEditByDetailsId(Long detailsId) {
        return baseMapper.selectByDetailsId(detailsId);
    }

    @Override
    public List<NewsEdit> getUserEditNewsList(Long loginId) {
        List<NewsEdit> newsEdits = baseMapper.selectByUserId(loginId);
        for (NewsEdit newsEdit : newsEdits) {
            NewsDetails newsDetails = newsDetailsService.getById(newsEdit.getDetailsId());
            NewsCategory category = categoryService.getById(newsEdit.getType());
            newsEdit.setFormatTime(DateUtils.getDateStrByTimestamp(newsEdit.getEditTime()));
            newsEdit.setNewsTitle(newsDetails.getNewsTitle());
            newsEdit.setCategoryName(category.getCategoryName());
        }
        return newsEdits;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String newsTitle = (String) params.get("newsTitle");
        String type = (String) params.get("type");
        String status = (String) params.get("status");
        List<Long> newsDetailsIds = new ArrayList<>();
        if (StringUtils.isNotBlank(newsTitle)) {
            newsDetailsIds = newsDetailsService.likeSelectByTitle(newsTitle);
        }

        IPage<NewsEdit> page = this.page(
                new Query<NewsEdit>().getPage(params, "edit_time", false),
                new QueryWrapper<NewsEdit>()
                        .in(newsDetailsIds.size() != 0, "details_id", newsDetailsIds)
                        .eq(StringUtils.isNotBlank(type), "type", type)
                        .eq(StringUtils.isNotBlank(status), "status", status)
        );

        return new PageUtils(page);
    }
}
