package com.graduate.engine.service;

import com.graduate.engine.model.viewobject.DocumentVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件相关服务
 * @author jimmy
 */
public interface FileService {
    Boolean test(MultipartFile file, Long taskExecId, String docCatagory, Boolean discover, Long personId);

    List<DocumentVo> getDocuments(Long taskExecId);
}
