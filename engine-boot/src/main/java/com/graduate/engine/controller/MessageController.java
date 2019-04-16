package com.graduate.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.engine.model.viewobject.MessageSummary;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.MessageService;
import com.graduate.engine.service.PersonMessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController extends AbstractController{
    @Resource
    private MessageService messageService;
    @Resource
    private PersonMessageService personMessageService;

    @ApiOperation("获取未读的信息数量")
    @GetMapping("/counts")
    public ResponseResult getUnreadCount() {
        return ResponseResult.buildSuccess(personMessageService.getUnreadCountByUserId(getUserId()));
    }

    @ApiOperation("获取消息")
    @GetMapping("/info")
    public ResponseResult getMessageInfo() {
        List<MessageSummary> unReadList = personMessageService.getUnreadList(getUserId());
        List<MessageSummary> readList = personMessageService.getReadList(getUserId());
        List<MessageSummary> trashList = personMessageService.getTrashList(getUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("unread",unReadList);
        jsonObject.put("readed",readList);
        jsonObject.put("trash",trashList);
        return ResponseResult.buildSuccess(jsonObject);
    }

    @ApiOperation("获取信息内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "消息id", required = true, dataType = "Long"),
    })
    @GetMapping("/info/{messageId}")
    public ResponseResult getMessageContent(@PathVariable("messageId") Long messageId) {
        String content = messageService.getContentById(messageId);
        return ResponseResult.buildSuccess(content);
    }

    @ApiOperation("更新消息状态为已读")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "消息id", required = true, dataType = "Long"),
    })
    @PostMapping("/info/{messageId}")
    public ResponseResult HasReadMessage(@PathVariable("messageId") Long messageId) {
        personMessageService.hasReadMessage(getUserId(),messageId);
        return ResponseResult.buildSuccess();
    }

    @ApiOperation("更新消息状态为删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "消息id", required = true, dataType = "Long"),
    })
    @DeleteMapping("/info/{messageId}")
    public ResponseResult removeMessage(@PathVariable("messageId") Long messageId) {
        personMessageService.removeMessage(getUserId(),messageId);
        return ResponseResult.buildSuccess();
    }

    @ApiOperation("还原消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "消息id", required = true, dataType = "Long"),
    })
    @PutMapping("/info/{messageId}")
    public ResponseResult restoreMessage(@PathVariable("messageId") Long messageId) {
        personMessageService.restoreMessage(getUserId(),messageId);
        return ResponseResult.buildSuccess();
    }
}
