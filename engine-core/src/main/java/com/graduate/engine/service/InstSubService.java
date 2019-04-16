package com.graduate.engine.service;

import com.graduate.engine.model.InstituteSub;

import java.util.List;

public interface InstSubService {
    /**
     * 通过学会id查询出学会分会List
     * @return
     */
    List<InstituteSub> getInstSubsByInstId(Long instId);
}
