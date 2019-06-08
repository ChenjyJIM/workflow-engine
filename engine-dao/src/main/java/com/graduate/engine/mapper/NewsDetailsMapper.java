package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.NewsDetails;

import java.util.List;

/**
 * <p>
 * 新闻内容表 Mapper 接口
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
public interface NewsDetailsMapper extends BaseMapper<NewsDetails> {
    List<Long> likeSelectByTitle(String title);
}
