package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.NewsEdit;

import java.util.List;

/**
 * <p>
 * 新闻编辑表 Mapper 接口
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
public interface NewsEditMapper extends BaseMapper<NewsEdit> {
    List<NewsEdit> selectByUserId(Long userId);

    NewsEdit selectByDetailsId(Long detailsId);
}
