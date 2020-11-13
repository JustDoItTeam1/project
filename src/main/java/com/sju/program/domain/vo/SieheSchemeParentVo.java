package com.sju.program.domain.vo;


import com.sju.program.domain.SiegeScheme;

import java.util.List;

public class SieheSchemeParentVo {

    private int Id;

    private Boolean ss;

    private Boolean ssmap;

    private int childrennum;

    private String ssBuilderName;

    private Long  ssProjectId;

    private String ssProjectName;


    private List<SiegeScheme> children;

    private String ssVerifyFlag;

    public String getSsVerifyFlag() {
        return ssVerifyFlag;
    }

    public void setSsVerifyFlag(String ssVerifyFlag) {
        this.ssVerifyFlag = ssVerifyFlag;
    }

    public List<SiegeScheme> getChildren() {
        return children;
    }

    public void setChild(List<SiegeScheme> children) {
        this.children = children;
    }

    public String getSsBuilderName() {
        return ssBuilderName;
    }

    public void setSsBuilderName(String ssBuilderName) {
        this.ssBuilderName = ssBuilderName;
    }

    public Long getSsProjectId() {
        return ssProjectId;
    }

    public void setSsProjectId(Long ssProjectId) {
        this.ssProjectId = ssProjectId;
    }

    public String getSsProjectName() {
        return ssProjectName;
    }

    public void setSsProjectName(String ssProjectName) {
        this.ssProjectName = ssProjectName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Boolean getSs() {
        return ss;
    }

    public void setSs(Boolean ss) {
        this.ss = ss;
    }

    public Boolean getSsmap() {
        return ssmap;
    }

    public void setSsmap(Boolean ssmap) {
        this.ssmap = ssmap;
    }

    public int getChildrennum() {
        return childrennum;
    }

    public void setChildrennum(int childrennum) {
        this.childrennum = childrennum;
    }
}
