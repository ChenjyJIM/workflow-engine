package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

/**
 * 分会每一届的分会信息
 */
@Data
public class InstSubPeriodVo {

    private Long instSubPeriodId;

    private Long instSubId;

    private Integer instSubPeriodNo;

    private String instSubPeriodFrom;

    private String instSubPeriodTo;

    private String instSubMemo;

    private List<PersonCharger> personChargers;

    private List<CompCharger> compChargers;

}
