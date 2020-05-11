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
 * 新闻内容表
 * </p>
 *
 * @author lianglili
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "NewsDetails", description = "新闻内容表")
public class NewsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "details_id", type = IdType.AUTO)
    private Long detailsId;

    @ApiModelProperty(value = "新闻标题")
    private String newsTitle;

    @ApiModelProperty(value = "作者名称")
    private String author;

    @TableField(exist = false)
    @ApiModelProperty(value = "新闻分类，外键 →NewsCategory.CategoryID")
    private Long type;

    @ApiModelProperty(value = "图片新闻图片地址，不是图片新闻为空")
    private String cover;

    @ApiModelProperty(value = "新闻内容")
    private String newsContent;


}
