package com.graduate.engine.mapper;

import com.graduate.engine.model.Document;

public interface DocumentMapper {
    int deleteByPrimaryKey(Long documentId);

    int insert(Document record);

    int insertSelective(Document record);

    Document selectByPrimaryKey(Long documentId);

    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);

    Document getByTaskExecId(String taskExecId);

}
