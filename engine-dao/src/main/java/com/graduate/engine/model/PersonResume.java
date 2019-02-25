package com.graduate.engine.model;

import lombok.Data;

@Data
public class PersonResume {
    private Integer personResumeId;

    private Integer instId;

    private Integer personId;

    private String personResumeComp;

    private String personResumeAddr;

    private String personResumeDept;

    private String personResumePost;

    private Integer personResumeRank;

    private String personResumeSpec;

    private Integer personResumeFrom;

    private Integer personResumeTo;

    private String memo;
}