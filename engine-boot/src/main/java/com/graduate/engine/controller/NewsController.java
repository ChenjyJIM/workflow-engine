package com.graduate.engine.controller;

import com.graduate.engine.exception.BasicException;
import com.graduate.engine.model.NewsCategory;
import com.graduate.engine.model.NewsDetails;
import com.graduate.engine.model.NewsEdit;
import com.graduate.engine.model.NewsPublish;
import com.graduate.engine.response.ResponseResult;
import com.graduate.engine.service.*;
import com.graduate.engine.utils.DateUtils;
import com.graduate.engine.utils.PageUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/news")
@RestController
public class NewsController extends AbstractController {
    @Resource
    NewsEditService newsEditService;
    @Resource
    NewsPublishService newsPublishService;
    @Resource
    NewsReviewService newsReviewService;
    @Resource
    NewsDetailsService newsDetailsService;
    @Resource
    NewsCategoryService newsCategoryService;

    @ApiOperation("新增新闻分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "分类名称", required = true, dataType = "NewsCategory")
    })
    @PostMapping("/category")
    public ResponseResult addNewsCategory(@RequestBody NewsCategory category) {
        newsCategoryService.save(category);
        return ResponseResult.buildSuccess("新增新闻分类成功");
    }

    @ApiOperation("更新新闻分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "分类名称", required = true, dataType = "NewsCategory")
    })
    @PutMapping("/category")
    public ResponseResult updateNewsCategory(@RequestBody NewsCategory category) {
        newsCategoryService.updateById(category);
        return ResponseResult.buildSuccess("更新新闻分类成功");
    }

    @ApiOperation("获取新闻分类")
    @GetMapping("/category")
    public ResponseResult getNewsCategoryList() {
        return ResponseResult.buildSuccess(newsCategoryService.list());
    }

    @ApiOperation("停用新闻分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类名称", required = true, dataType = "Long")
    })
    @DeleteMapping("/category/{categoryId}")
    public ResponseResult stopNewsCategory(@PathVariable("categoryId") Long categoryId) {
        NewsCategory category = newsCategoryService.getById(categoryId);
        category.setStop(true);
        newsCategoryService.updateById(category);
        return ResponseResult.buildSuccess("停用新闻分类成功");
    }

    @ApiOperation("获取我的新闻列表")
    @GetMapping("/edit/list")
    public ResponseResult getNewsEditList() {
        return ResponseResult.buildSuccess(newsEditService.getUserEditNewsList(getUserId()));
    }

    @ApiOperation("编写新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "分类类别", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "newsDetails", value = "新闻详情信息", required = true, dataType = "NewsDetails")
    })
    @PostMapping("/edit/details/{type}")
    public ResponseResult editNews(@PathVariable("type") Long type, @RequestBody NewsDetails newsDetails) {
        verifyForm(newsDetails);
        NewsEdit newsEdit = new NewsEdit();
        newsEdit.setType(type);
        newsEdit.setEditPersonId(getUserId());
        newsEdit.setEditTime(DateUtils.getCurrentSecondTimestamp());
        newsEdit.setStatus(0L);
        newsEditService.editNews(newsEdit, newsDetails);
        return ResponseResult.buildSuccess("新闻撰写成功，请等待审核！");
    }

    @ApiOperation("获取我的新闻详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "分类类别", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "detailsId", value = "新闻详情ID", required = true, dataType = "Long")
    })
    @GetMapping("/edit/details/{type}/{detailsId}")
    public ResponseResult getNewsDetails(@PathVariable("type") Long type, @PathVariable("detailsId") Long detailsId) {
        NewsDetails details = newsDetailsService.getById(detailsId);
        details.setType(type);
        return ResponseResult.buildSuccess(details);
    }

    @ApiOperation("修改新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "分类类别", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "newsDetails", value = "新闻详情信息", required = true, dataType = "NewsDetails")
    })
    @PutMapping("/edit/details/{type}")
    public ResponseResult updateNews(@PathVariable("type") Long type, @RequestBody NewsDetails newsDetails) {
        verifyForm(newsDetails);
        //判断新闻是否发布
        NewsEdit newsEdit = newsEditService.selectEditByDetailsId(newsDetails.getDetailsId());
        if (newsEdit.getStatus() == 1) {
            //如果审核通过发布，不能修改，需联系管理员停用后重新发布
            return ResponseResult.buildError("原新闻已发布，无法修改，如需修改请联系审核员停用原新闻");
        } else {
            //如果发布新闻停用、待审核或者审核未通过，则会修改原新闻，并将状态重新设为未审核
            newsEdit.setType(type);
            newsEditService.updateNews(newsEdit, newsDetails);
            return ResponseResult.buildSuccess("修改成功，请等待审核！");
        }
    }

    @ApiOperation("获取发布新闻列表")
    @GetMapping("/review/list/publish")
    public ResponseResult getPublishNewsList(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = newsPublishService.queryPage(params);
        List<NewsPublish> publishList = (List<NewsPublish>) pageUtils.getList();
        for (NewsPublish publish : publishList) {
            publish.setFormatTime(DateUtils.getDateStrByTimestamp(publish.getPublishTime()));
            publish.setCategoryName(newsCategoryService.getById(publish.getType()).getCategoryName());
            publish.setNewsTitle(newsDetailsService.getById(publish.getDetailsId()).getNewsTitle());
        }
        pageUtils.setList(publishList);
        return ResponseResult.buildSuccess(pageUtils);
    }

    @ApiOperation("停用已发布的新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "publishId", value = "发布信息ID", required = true, dataType = "Long")
    })
    @DeleteMapping("/review/publish/{publishId}")
    public ResponseResult stopPublishNews(@PathVariable("publishId") Long publishId) {
        newsPublishService.stopPublishNews(publishId);
        return ResponseResult.buildSuccess("停用成功");
    }

    @ApiOperation("恢复已发布的新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "publishId", value = "发布信息ID", required = true, dataType = "Long")
    })
    @PutMapping("/review/publish/{publishId}")
    public ResponseResult republishNews(@PathVariable("publishId") Long publishId) {
        newsPublishService.republishNews(publishId);
        return ResponseResult.buildSuccess("恢复成功");
    }

    @ApiOperation("获取全部新闻列表")
    @GetMapping("/review/list")
    public ResponseResult getAllNewsList(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = newsEditService.queryPage(params);
        List<NewsEdit> editList = (List<NewsEdit>) pageUtils.getList();
        for (NewsEdit edit : editList) {
            edit.setFormatTime(DateUtils.getDateStrByTimestamp(edit.getEditTime()));
            edit.setCategoryName(newsCategoryService.getById(edit.getType()).getCategoryName());
            edit.setNewsTitle(newsDetailsService.getById(edit.getDetailsId()).getNewsTitle());
        }
        pageUtils.setList(editList);
        return ResponseResult.buildSuccess(pageUtils);
    }

    @ApiOperation("新闻审核不通过")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "publishId", value = "新闻编辑表ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "message", value = "新闻不通过原因", required = true, dataType = "String")
    })
    @DeleteMapping("/review/{editId}/{message}")
    public ResponseResult disagreeNews(@PathVariable("editId") Long editId, @PathVariable("message") String message) {
        newsReviewService.reviewNews(editId, message, getUserId());
        return ResponseResult.buildSuccess("审核成功");
    }

    @ApiOperation("新闻审核通过")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "editId", value = "新闻编辑表ID", required = true, dataType = "Long")
    })
    @PostMapping("/review/{editId}")
    public ResponseResult agreeNews(@PathVariable("editId") Long editId) {
        newsReviewService.reviewNews(editId, getUserId());
        return ResponseResult.buildSuccess("审核成功");
    }

    private void verifyForm(NewsDetails details) {
        if (StringUtils.isBlank(details.getNewsTitle())) {
            throw new BasicException("新闻标题不能为空");
        }

        if (StringUtils.isBlank(details.getAuthor())) {
            throw new BasicException("新闻作者不能为空");
        }

        if (StringUtils.isBlank(details.getNewsContent())) {
            throw new BasicException("新闻内容不能为空");
        }
    }
}
