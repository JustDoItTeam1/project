package com.sju.program.domain.vo;


import com.sju.program.domain.SiegeScheme;

import java.util.List;

public class SieheSchemeParentVo {

    private Long ssId;

    private Boolean ss;

    private Boolean ssmap;

    private int childrennum;

    private String ssBuilderName;

    private Long  ssProjectId;

    private String ssProjectName;

    private String ssStatus;

    private String ssLane;

    private String ssStage;

    private String ssStartTime;

    private String ssEndTime;

    private String ssRange;

    private String ssProperties;

    private String ssSuggessions;

    private String ssFilePath;

    private Long ssBuilderId;

    private Long ssTrafficStaffId;

    private Long ssDeleteFlag;

    private Long ssVerifyDate;




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

    public Long getSsId() {
        return ssId;
    }

    public void setSsId(Long ssId) {
        this.ssId = ssId;
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

    public String getSsStatus() {
        return ssStatus;
    }

    public void setSsStatus(String ssStatus) {
        this.ssStatus = ssStatus;
    }

    public String getSsLane() {
        return ssLane;
    }

    public void setSsLane(String ssLane) {
        this.ssLane = ssLane;
    }

    public String getSsStage() {
        return ssStage;
    }

    public void setSsStage(String ssStage) {
        this.ssStage = ssStage;
    }

    public String getSsStartTime() {
        return ssStartTime;
    }

    public void setSsStartTime(String ssStartTime) {
        this.ssStartTime = ssStartTime;
    }

    public String getSsEndTime() {
        return ssEndTime;
    }

    public void setSsEndTime(String ssEndTime) {
        this.ssEndTime = ssEndTime;
    }

    public String getSsRange() {
        return ssRange;
    }

    public void setSsRange(String ssRange) {
        this.ssRange = ssRange;
    }

    public String getSsProperties() {
        return ssProperties;
    }

    public void setSsProperties(String ssProperties) {
        this.ssProperties = ssProperties;
    }

    public String getSsSuggessions() {
        return ssSuggessions;
    }

    public void setSsSuggessions(String ssSuggessions) {
        this.ssSuggessions = ssSuggessions;
    }

    public String getSsFilePath() {
        return ssFilePath;
    }

    public void setSsFilePath(String ssFilePath) {
        this.ssFilePath = ssFilePath;
    }

    public Long getSsBuilderId() {
        return ssBuilderId;
    }

    public void setSsBuilderId(Long ssBuilderId) {
        this.ssBuilderId = ssBuilderId;
    }

    public Long getSsTrafficStaffId() {
        return ssTrafficStaffId;
    }

    public void setSsTrafficStaffId(Long ssTrafficStaffId) {
        this.ssTrafficStaffId = ssTrafficStaffId;
    }

    public Long getSsDeleteFlag() {
        return ssDeleteFlag;
    }

    public void setSsDeleteFlag(Long ssDeleteFlag) {
        this.ssDeleteFlag = ssDeleteFlag;
    }

    public Long getSsVerifyDate() {
        return ssVerifyDate;
    }

    public void setSsVerifyDate(Long ssVerifyDate) {
        this.ssVerifyDate = ssVerifyDate;
    }

    public void setChildren(List<SiegeScheme> children) {
        this.children = children;
    }
}
