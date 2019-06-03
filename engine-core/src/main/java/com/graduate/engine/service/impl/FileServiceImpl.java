package com.graduate.engine.service.impl;

import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.*;
import com.graduate.engine.model.Document;
import com.graduate.engine.model.Task;
import com.graduate.engine.model.viewobject.DocumentVo;
import com.graduate.engine.service.FileService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.utils.FileUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileServiceImpl implements FileService {

    private static final Pattern DOT_VERSION_PATTERN = Pattern.compile("-docv(\\d+?).");

    private static final String PREFIX_NAME = "-docv";
    @Resource
    private DocumentMapper documentMapper;

    @Resource
    private TaskExecMapper taskExecMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private PersonMemberMapper personMemberMapper;

    public static void main(String[] args) {
        String testString = "开题报告-docv1.txt";
        Long versionInFile = 0L;
        Matcher matcher = DOT_VERSION_PATTERN.matcher(testString);
        if (matcher.find()) {
            versionInFile = Long.parseLong(matcher.group(1));
        }
        System.out.println(testString.replace(PREFIX_NAME.concat(versionInFile.toString()),"").trim());
    }
    // 开题报告-docv1.txt
    @Override
    public Boolean test(MultipartFile file, Long taskExecId, String docCatagory, Boolean discover, Long personId) {
        String originalFilename = file.getOriginalFilename();
        Document document = new Document();
        Long versionInDatabase = documentMapper.getVersionByTaskExecId(taskExecId);
        if (discover) {
            Long versionInFile;
            Matcher matcher = DOT_VERSION_PATTERN.matcher(originalFilename);
            if (matcher.find()) {
                versionInFile = Long.parseLong(matcher.group(1));
                Document originalDocument = documentMapper.getIdByExecIdAndVersion(taskExecId, versionInFile);
                String uniqueName = UUID.randomUUID().toString().replace("-", "");
                Task task = taskMapper.selectByPrimaryKey(taskExecMapper.selectByPrimaryKey(taskExecId).getTaskId());
                String filePath = "files/" + activityMapper.selectByPrimaryKey(task.getActId()).getActName() + "/" + task.getTaskName() + "/";
                try {
                    FileUtil.uploadFile(file.getBytes(), filePath, uniqueName + originalFilename.substring(originalFilename.lastIndexOf(".")));
                } catch (Exception e) {
                    throw new BusinessException("文件写入失败！");
                }
                document.setGmtModified(DateUtils.getCurrentSecondTimestamp());
                document.setDocumentId(originalDocument.getDocumentId());
                document.setTaskExecId(taskExecId);
                document.setDocName(originalFilename.replace(PREFIX_NAME.concat(versionInFile.toString()),"").trim());
                document.setDocClass(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
                document.setDocCatagory(docCatagory);
                document.setDocPath(filePath);
                document.setDocUniqueName(uniqueName);
                document.setPersonId(personId);
                documentMapper.updateByPrimaryKeySelective(document);
                return true;
            } else {
                throw new BusinessException("文件格式错误！");
            }
        } else {
            String uniqueName = UUID.randomUUID().toString().replace("-", "");
            Task task = taskMapper.selectByPrimaryKey(taskExecMapper.selectByPrimaryKey(taskExecId).getTaskId());
            String filePath = "files/" + activityMapper.selectByPrimaryKey(task.getActId()).getActName() + "/" + task.getTaskName() + "/";
            try {
                FileUtil.uploadFile(file.getBytes(), filePath, uniqueName + originalFilename.substring(originalFilename.lastIndexOf(".")));
            } catch (Exception e) {
                throw new BusinessException("文件写入失败！");
            }
            document.setTaskExecId(taskExecId);
            // 仅做一次校验，若上传文件含有特定前缀，过滤一层
            if (originalFilename.contains(PREFIX_NAME)) {
                Long versionCount = 0L;
                Matcher matcher = DOT_VERSION_PATTERN.matcher(originalFilename);
                if (matcher.find()) {
                    versionCount = Long.parseLong(matcher.group(1));
                }
                document.setDocName(originalFilename.replace(PREFIX_NAME.concat(versionCount.toString()),"").trim());
            } else {
                document.setDocName(originalFilename);
            }
            document.setGmtModified(DateUtils.getCurrentSecondTimestamp());
            document.setDocClass(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
            document.setDocCatagory(docCatagory);
            document.setDocPath(filePath);
            document.setDocUniqueName(uniqueName);
            document.setPersonId(personId);
            document.setVersion(versionInDatabase == null? 1 : versionInDatabase + 1L);
            return 1 == documentMapper.insertSelective(document);
        }

    }

    @Override
    public List<DocumentVo> getDocuments(Long taskExecId) {
        List<Document> documents = documentMapper.getByTaskExecId(taskExecId);
        List<DocumentVo> results = BeanUtils.copyListWithBeans(documents, DocumentVo.class);
        results.forEach(result -> {
            result.setPersonName(personMemberMapper.selectByPrimaryKey(result.getPersonId()).getName());
            result.setModifyDate(DateUtils.getDateStrByTimestamp(result.getGmtModified()));
        });
        return results;
    }


}
