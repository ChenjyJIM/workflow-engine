package com.graduate.engine.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 新闻审核表
 * </p>
 *
 * @author lianglili
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "NewsReview", description = "新闻审核表")
public class NewsReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "review_id", type = IdType.AUTO)
    private Long reviewId;

    @ApiModelProperty(value = "新闻编辑表ID，外键 →NewsEdit.EditID")
    private Long editId;

    @ApiModelProperty(value = "审核时间")
    private Long reviewTime;

    @ApiModelProperty(value = "审核人ID，外键 →Login.LoginID")
    private Long reviewPersonId;

    @ApiModelProperty(value = "不通过填写原因")
    private String reviewMessage;


}
