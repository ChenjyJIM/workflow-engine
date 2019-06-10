package com.graduate.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.engine.model.CheckIn;

/**
 * <p>
 * 报名签到 Mapper 接口
 * </p>
 *
 * @author silicon
 * @since 2019-06-09
 */
public interface CheckInMapper extends BaseMapper<CheckIn> {
    CheckIn getBySignId(Long signId);
}
