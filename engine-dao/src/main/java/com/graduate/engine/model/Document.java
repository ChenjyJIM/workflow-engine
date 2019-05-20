package com.graduate.engine.model;

import lombok.Data;

@Data
public class Document {
    private Long documentId;

    private Long taskExecId;

    private String docPath;

    private String docUniqueName;

    private String docClass;

    private String docCatagory;

    private String docName;

    private Long personId;

    private Long version;

    private Long gmtModified;
}
