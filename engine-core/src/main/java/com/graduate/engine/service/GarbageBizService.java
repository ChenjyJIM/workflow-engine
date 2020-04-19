package com.graduate.engine.service;

import com.graduate.engine.model.GarbageDetail;
import com.graduate.engine.model.ObjectGarbageMapping;
import com.graduate.engine.model.viewobject.ObjectGarbageVO;
import com.graduate.engine.request.ObjectQuery;
import com.graduate.engine.response.PagedResult;

import java.util.List;

public interface GarbageBizService {

    /**
     * 根据条件查询物品-垃圾种类
     * @param query
     * @return
     */
    PagedResult<ObjectGarbageVO> getAllObjects(ObjectQuery query);

    /**
     * 获取所有垃圾类别
     * @return
     */
    List<GarbageDetail> getGarbageTypeList();

    /**
     * 新增垃圾类别
     * @param garbageDetail
     * @return
     */
    boolean save(GarbageDetail garbageDetail);

    /**
     * 更新垃圾类别
     * @param garbageDetail
     * @return
     */
    boolean updateById(GarbageDetail garbageDetail);

    /**
     * 停用垃圾类别
     * @param garbageId
     */
    void stopGarbageType(Long garbageId);

    /**
     * 新增垃圾分类
     * @param objectGarbageMapping
     * @return
     */
    boolean saveObjectGarbageMapping(ObjectGarbageMapping objectGarbageMapping);


    /**
     * 更新垃圾类别
     * @param objectGarbageMapping
     * @return
     */
    boolean updateObjectGarbageMappingById(ObjectGarbageMapping objectGarbageMapping);

    /**
     * 停用垃圾类别
     * @param objectGarbageMappingId
     */
    void stopObjectGarbageMapping(Long objectGarbageMappingId);
}
