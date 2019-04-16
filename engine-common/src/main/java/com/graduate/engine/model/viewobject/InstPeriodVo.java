package com.graduate.engine.model.viewobject;

import lombok.Data;

import java.util.List;

/**
 * 学会每一届学会的信息
 * @author jimmy
 */
@Data
public class InstPeriodVo {

    private Long instPeriodId;

    private Long instId;

    private Integer instPeriodNo;

    private String instPeriodFrom;

    private String instPeriodTo;

    private String instMemo;

    private List<PersonCharger> personChargers;

    private List<CompCharger> compChargers;
}
