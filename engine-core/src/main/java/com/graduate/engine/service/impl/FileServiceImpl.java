package com.graduate.engine.service.impl;

import com.graduate.engine.mapper.DocumentMapper;
import com.graduate.engine.model.Document;
import com.graduate.engine.service.FileService;
import com.graduate.engine.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private DocumentMapper documentMapper;

    @Override
    public Boolean test(MultipartFile file, Long taskExecId, String docCatagory) {
        Document document = new Document();
        String originalFilename = file.getOriginalFilename();
        //todo 根据execId获取到task信息和act信息，动态生成路径
        String uniqueName = UUID.randomUUID().toString().replace("-", "");
        String filePath = "files/activity1/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, uniqueName + originalFilename.substring(originalFilename.lastIndexOf(".")));
        } catch (Exception e) {
            System.out.println(e);
        }
        document.setTaskExecId(taskExecId);
        document.setDocName(originalFilename);
        document.setDocClass(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
        document.setDocCatagory(docCatagory);
        document.setDocPath(filePath);
        document.setDocUniqueName(uniqueName);
        return 1 == documentMapper.insertSelective(document);
    }
}
