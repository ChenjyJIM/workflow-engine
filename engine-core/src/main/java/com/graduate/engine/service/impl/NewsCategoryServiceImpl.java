package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.model.NewsCategory;
import com.graduate.engine.mapper.NewsCategoryMapper;
import com.graduate.engine.service.NewsCategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻类别表 服务实现类
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
@Service
public class NewsCategoryServiceImpl extends ServiceImpl<NewsCategoryMapper, NewsCategory> implements NewsCategoryService {

}
