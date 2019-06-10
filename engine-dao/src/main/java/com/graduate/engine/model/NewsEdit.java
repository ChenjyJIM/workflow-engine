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
 * 新闻编辑表
 * </p>
 *
 * @author silicon
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "NewsEdit", description = "新闻编辑表")
public class NewsEdit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "edit_id", type = IdType.AUTO)
    private Long editId;

    @ApiModelProperty(value = "新闻内容ID，外键 →NewsDetails.DetailsID")
    private Long detailsId;

    @TableField(exist = false)
    @ApiModelProperty(value = "新闻标题")
    private String newsTitle;

    @TableField(exist = false)
    @ApiModelProperty(value = "编辑时间")
    private String formatTime;

    @ApiModelProperty(value = "编辑时间戳")
    private Long editTime;

    @ApiModelProperty(value = "状态，0：待审核；1：审核通过；2：审核不通过;3：发布后停用")
    private Long status;

    @ApiModelProperty(value = "新闻编辑者ID，外键 →Login.LoginID")
    private Long editPersonId;

    @ApiModelProperty(value = "新闻分类，外键 →NewsCategory.CategoryID")
    private Long type;

    @TableField(exist = false)
    @ApiModelProperty(value = "新闻分类名称")
    private String categoryName;

}
