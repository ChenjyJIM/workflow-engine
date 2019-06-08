package com.graduate.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.engine.model.NewsPublish;
import com.graduate.engine.utils.PageUtils;

import java.util.Map;

/**
 * <p>
 * 新闻发布表 服务类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
public interface NewsPublishService extends IService<NewsPublish> {
    //获取发布新闻列表，可选项：
    //withoutStop 列表中是否不包含停用新闻
    //type 新闻分类
    //newsTitle 新闻标题，支持模糊查询
    PageUtils queryPage(Map<String, Object> params);
    //停用发布新闻
    void stopPublishNews(Long publishId);
    //恢复发布新闻
    void republishNews(Long publishId);
}
