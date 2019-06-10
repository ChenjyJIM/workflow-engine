package com.graduate.engine.controller;

import com.graduate.engine.mapper.ActivityMapper;
import com.graduate.engine.mapper.DocumentMapper;
import com.graduate.engine.mapper.TaskExecMapper;
import com.graduate.engine.mapper.TaskMapper;
import com.graduate.engine.model.Document;
import com.graduate.engine.model.Task;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文档管理相关接口
 *
 * @author jimmy
 */
@RequestMapping("/upload")
@RestController
public class UploadController extends AbstractController {
    @Resource
    private FileService fileService;

    @Resource
    private DocumentMapper documentMapper;

    @Resource
    private TaskExecMapper taskExecMapper;

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private ActivityMapper activityMapper;


    @PostMapping("/test")
    public ResponseResult test(MultipartFile file, Long taskExecId, String docCatagory, Long personId, boolean discover) {
        if (file == null || taskExecId == null) {
            return ResponseResult.buildError("导入文件为空！");
        }
        try {
            if (fileService.test(file, taskExecId, docCatagory, discover, personId)) {
                return ResponseResult.buildSuccess("文件 解析成功");
            } else {
                return ResponseResult.buildError("文件 解析失败");
            }
        } catch (Exception e) {
            return ResponseResult.buildError("文件 解析失败");
        }
    }

    @GetMapping("/getDocuments")
    public ResponseResult getDocuments(Long taskExecId) {
        if (taskExecId == null) {
            return ResponseResult.buildError("参数不为空！");
        }
        try {
            return ResponseResult.buildSuccess(fileService.getDocuments(taskExecId));
        } catch (Exception e) {
            return ResponseResult.buildError("系统异常！");
        }
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置文件名
        String documentId = request.getParameter("documentId");

        // todo 换成主键
        Document document = documentMapper.selectByPrimaryKey(Long.parseLong(documentId));
        if (document == null) {
            return;
        }

        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            Task task = taskMapper.selectByPrimaryKey(taskExecMapper.selectByPrimaryKey(document.getTaskExecId()).getTaskId());
            String filePath = "files/" + activityMapper.selectByPrimaryKey(task.getActId()).getActName() + "/" + task.getTaskName() + "/";

            File file = new File(filePath + document.getDocUniqueName() + "." + document.getDocClass());

            String docName = document.getDocName();
            StringBuilder fileName = new StringBuilder(docName.substring(0, docName.lastIndexOf(".")));
            fileName.append("-docv" + document.getVersion()).append(docName.substring(docName.lastIndexOf(".")));
            fis = new FileInputStream(file);

            response.setHeader("Content-Disposition", "attachment; fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName.toString(), "UTF-8"));
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
