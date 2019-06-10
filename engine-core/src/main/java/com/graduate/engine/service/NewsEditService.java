package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.NewsDetails;
import com.graduate.engine.model.NewsEdit;
import com.graduate.engine.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 新闻编辑表 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
public interface NewsEditService extends IService<NewsEdit> {

    void editNews(NewsEdit newsEdit, NewsDetails newsDetails);

    void updateNews(NewsEdit newsEdit, NewsDetails newsDetails);

    NewsEdit selectEditByDetailsId(Long detailsId);

    List<NewsEdit> getUserEditNewsList(Long loginId);

    /**
     * 获取全部新闻列表，可选项：
     * newsTitle 新闻标题，支持模糊查询
     * type 新闻类型
     * status 新闻状态
     */
    PageUtils queryPage(Map<String, Object> params);
}
