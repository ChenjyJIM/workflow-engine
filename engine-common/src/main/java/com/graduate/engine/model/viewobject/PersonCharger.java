package com.graduate.engine.model.viewobject;

import com.graduate.engine.model.dataobject.PersonMember;
import lombok.Data;

/**
 * 学会或分会 负责人信息
 *
 * @author jimmy
 */
@Data
public class PersonCharger {

    /**
     * 数据库主键id
     */
    private Long periodPersonId;

    private Integer periodPersonOrder;

    /**
     * 个人理事头衔名称
     */
    private String personTitleName;
    /**
     * 个人理事详细信息
     */
    private PersonMember personMember;

    private String memo;
}
