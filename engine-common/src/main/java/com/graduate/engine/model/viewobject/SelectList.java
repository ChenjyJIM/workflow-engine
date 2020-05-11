package com.graduate.engine.model.viewobject;

import lombok.Data;

/**
 * 用于前端select展示
 *
 * @author lianglili
 */
@Data
public class SelectList {

    //          id: 3,
    //          value: '百度',
    //          label: '百度',
    //          content: '百度简称狼厂'
    private Long id;
    private String value;
    private String label;
    private String content;

}
