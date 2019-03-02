package com.graduate.engine.model;

public class Document {
    private Integer documentId;

    private Integer taskExecId;

    private String docPath;

    private String docUniqueName;

    private String docClass;

    private String docCatagory;

    private String docName;

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getTaskExecId() {
        return taskExecId;
    }

    public void setTaskExecId(Integer taskExecId) {
        this.taskExecId = taskExecId;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath == null ? null : docPath.trim();
    }

    public String getDocUniqueName() {
        return docUniqueName;
    }

    public void setDocUniqueName(String docUniqueName) {
        this.docUniqueName = docUniqueName == null ? null : docUniqueName.trim();
    }

    public String getDocClass() {
        return docClass;
    }

    public void setDocClass(String docClass) {
        this.docClass = docClass == null ? null : docClass.trim();
    }

    public String getDocCatagory() {
        return docCatagory;
    }

    public void setDocCatagory(String docCatagory) {
        this.docCatagory = docCatagory == null ? null : docCatagory.trim();
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }
}