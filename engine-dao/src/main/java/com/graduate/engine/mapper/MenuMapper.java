package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> queryListByParentId(Long parentId);
}
