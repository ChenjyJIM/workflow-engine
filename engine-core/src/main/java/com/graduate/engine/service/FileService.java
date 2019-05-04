package com.graduate.engine.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件相关服务
 */
public interface FileService {
    Boolean test(MultipartFile file, Long taskExecId, String docCatagory);
}
