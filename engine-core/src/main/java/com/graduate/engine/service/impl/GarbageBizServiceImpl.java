package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.engine.mapper.GarbageDetailMapper;
import com.graduate.engine.mapper.ObjectGarbageMappingMapper;
import com.graduate.engine.model.GarbageDetail;
import com.graduate.engine.model.ObjectGarbageMapping;
import com.graduate.engine.model.viewobject.ObjectGarbageVO;
import com.graduate.engine.request.ObjectQuery;
import com.graduate.engine.response.PagedResult;
import com.graduate.engine.service.GarbageBizService;
import com.graduate.engine.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarbageBizServiceImpl implements GarbageBizService {

    @Resource
    private GarbageDetailMapper garbageDetailMapper;

    @Resource
    private ObjectGarbageMappingMapper objectGarbageMappingMapper;

    @Override
    public PagedResult<ObjectGarbageVO> getAllObjects(ObjectQuery query) {
        PagedResult<ObjectGarbageVO> pagedResult = new PagedResult<>();
        pagedResult.setPage(query.getPage());
        pagedResult.setSize(query.getSize());

        String name = query.getName();
        Long garbageType = query.getGarbageType();

        LambdaQueryWrapper<ObjectGarbageMapping> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like(ObjectGarbageMapping::getName, name);
        }
        if (garbageType != null) {
            queryWrapper.eq(ObjectGarbageMapping::getGarbageType, garbageType);
        }

        IPage<ObjectGarbageMapping> page = objectGarbageMappingMapper.selectPage(new Page<>(query.getPage(),
                        query.getSize()),
                queryWrapper);
        pagedResult.setTotal(page.getTotal());
        List<ObjectGarbageMapping> garbageMappings = page.getRecords();


        List<ObjectGarbageVO> garbageVOS = null;
        try {
            garbageVOS = garbageMappings.stream()
                    .map(this::convert2VO)
                    .collect(Collectors.toList());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        pagedResult.setItems(garbageVOS);
        return pagedResult;
    }

    @Override
    public List<GarbageDetail> getGarbageTypeList() {
        LambdaQueryWrapper<GarbageDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(GarbageDetail::getId);
        return garbageDetailMapper.selectList(queryWrapper);
    }

    @Override
    public boolean save(GarbageDetail garbageDetail) {
        return 1 == garbageDetailMapper.insert(garbageDetail);
    }

    @Override
    public boolean updateById(GarbageDetail garbageDetail) {
        return 1 == garbageDetailMapper.updateById(garbageDetail);
    }

    @Override
    public void stopGarbageType(Long garbageId) {
        garbageDetailMapper.deleteById(garbageId);
    }

    @Override
    public boolean saveObjectGarbageMapping(ObjectGarbageMapping objectGarbageMapping) {
        return 1 == objectGarbageMappingMapper.insert(objectGarbageMapping);

    }

    @Override
    public boolean updateObjectGarbageMappingById(ObjectGarbageMapping objectGarbageMapping) {
        return 1 == objectGarbageMappingMapper.updateById(objectGarbageMapping);
    }

    @Override
    public void stopObjectGarbageMapping(Long objectGarbageMappingId) {
        objectGarbageMappingMapper.deleteById(objectGarbageMappingId);
    }

    private ObjectGarbageVO convert2VO(ObjectGarbageMapping objectGarbageMapping) {
        ObjectGarbageVO objectGarbageVO = BeanUtils.copyBean(objectGarbageMapping, ObjectGarbageVO.class);
        LambdaQueryWrapper<GarbageDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GarbageDetail::getId, objectGarbageMapping.getGarbageType());
        GarbageDetail detail = garbageDetailMapper.selectOne(queryWrapper);
        objectGarbageVO.setGarbageName(detail.getName());
        objectGarbageVO.setGarbageDetail(detail.getDetail());
        objectGarbageVO.setImg(detail.getImg());
        return objectGarbageVO;
    }
}
