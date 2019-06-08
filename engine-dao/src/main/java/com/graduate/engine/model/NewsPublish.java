package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 新闻发布表
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "NewsPublish", description = "新闻发布表")
public class NewsPublish implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "publish_id", type = IdType.AUTO)
    private Long publishId;

    @ApiModelProperty(value = "新闻内容ID，外键 →NewsDetails.DetailsID")
    private Long detailsId;

    @TableField(exist=false)
    @ApiModelProperty(value = "新闻标题")
    private String newsTitle;

    @ApiModelProperty(value = "发布时间戳")
    private Long publishTime;

    @TableField(exist=false)
    @ApiModelProperty(value = "发布时间")
    private String formatTime;

    @ApiModelProperty(value = "审核表ID，外键 →NewsReview.ReviewID")
    private Long reviewId;

    @ApiModelProperty(value = "新闻分类，外键 →NewsCategory.CategoryID")
    private Long type;

    @TableField(exist=false)
    @ApiModelProperty(value = "新闻分类名称")
    private String categoryName;

    private Boolean stop;


}
