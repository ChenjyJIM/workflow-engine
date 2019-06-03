package com.graduate.engine.mapper;

import com.graduate.engine.model.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMapper {
    int deleteByPrimaryKey(Long documentId);

    int insert(Document record);

    int insertSelective(Document record);

    Document selectByPrimaryKey(Long documentId);

    int updateByPrimaryKeySelective(Document record);

    int updateByPrimaryKey(Document record);

    List<Document> getByTaskExecId(Long taskExecId);

    /**
     * 通过execId拿到当前执行阶段最新的version
     * @param taskExecId
     * @return
     */
    Long getVersionByTaskExecId(Long taskExecId);

    /**
     * 根据版本号拿到主键用于更新
     * @param taskExecId
     * @param versionInFile
     * @return
     */
    Document getIdByExecIdAndVersion(@Param("taskExecId") Long taskExecId, @Param("versionInFile") Long versionInFile);
}
