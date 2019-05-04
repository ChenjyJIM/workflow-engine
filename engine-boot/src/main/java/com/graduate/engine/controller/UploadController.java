package com.graduate.engine.controller;

import com.graduate.engine.mapper.DocumentMapper;
import com.graduate.engine.model.Document;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.FileService;
import com.graduate.engine.utils.FileUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文档管理相关接口
 * @author jimmy
 */
@RequestMapping("/upload")
@RestController
public class UploadController {
    @Resource
    private FileService fileService;

    @Resource
    private DocumentMapper documentMapper;


    @PostMapping("/test")
    public ResponseResult test(MultipartFile file, Long taskExecId, String docCatagory) {
        if (file == null) {
            return ResponseResult.buildError("导入文件为空！");
        }
        try {
            if (fileService.test(file, taskExecId, docCatagory)) {
                return ResponseResult.buildSuccess("文件 解析成功");
            } else {
                return ResponseResult.buildError("文件 解析失败");
            }
        } catch (Exception e) {
            return ResponseResult.buildError("文件 解析失败");
        }
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置文件名
        String taskExecId = request.getParameter("taskExecId");
        Document document = documentMapper.getByTaskExecId(taskExecId);


        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {

            File file = new File("files/activity1/" +document.getDocUniqueName() + "." + document.getDocClass());

            String fileName = document.getDocName();
            fis = new FileInputStream(file);

            response.setHeader("Content-Disposition", "attachment; fileName="+  fileName +";filename*=utf-8''"+URLEncoder.encode(fileName,"UTF-8"));
            IOUtils.copy(fis,response.getOutputStream());
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
