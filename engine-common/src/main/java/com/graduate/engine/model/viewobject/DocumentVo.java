package com.graduate.engine.model.viewobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentVo {
    private Long documentId;

    private Long taskExecId;

    private String docPath;

    private String docUniqueName;

    private String docClass;

    private String docCatagory;

    private String docName;

    private Long personId;

    private Long version;

    private String personName;

    private Long gmtModified;

    private String modifyDate;
}
