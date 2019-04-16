package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.InstituteSubMapper;
import com.graduate.engine.model.InstituteSub;
import com.graduate.engine.service.InstSubService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jimmy
 */
@Service
public class InstSubServiceImpl implements InstSubService {

    @Resource
    private InstituteSubMapper instituteSubMapper;

    @Override
    public List<InstituteSub> getInstSubsByInstId(Long instId) {
        return instituteSubMapper.getInstSubsByInstId(instId);
    }


}
