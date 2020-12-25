package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 施工项目对象 project
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class Project
{
    private static final long serialVersionUID = 1L;

    /** 项目id */
    private Long projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目位置(文字描述) */
    @Excel(name = "项目位置(文字描述)")
    private String projectLocation;

    /** 项目位置(地图) */
    @Excel(name = "项目位置(地图)")
    private String projectLongLat;

    /** 负责人 */
    @Excel(name = "负责人")
    private String projectManger;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String projectPhone;

    /** 施工单位名称 */
    @Excel(name = "施工单位名称")
    private String projectBuilderName;

    /** 施工单位id */
    @Excel(name = "施工单位id")
    private Long projectBuilderId;

    /** 创建的交管人员id */
    @Excel(name = "创建的交管人员id")
    private Long projectTrafficStaffId;

    /** 项目完工标识 */
    @Excel(name = "项目完工标识")
    private String projectFinishedFlag;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String projectDeleteFlag;

    /** 项目是否按时提交进度的标识*/
    private String projectSubmiteprocessFlag;

//    /** 更新时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date projectUpdateFlag;

    /** 模糊查询 */
    @Excel(name = "模糊查询标识")
    private String projectInfo;
    /** 查看通过或者未通过方案的标识*/
    private String flag;

    public String getProjectSubmiteprocessFlag() {
        return projectSubmiteprocessFlag;
    }

    public void setProjectSubmiteprocessFlag(String projectSubmiteprocessFlag) {
        this.projectSubmiteprocessFlag = projectSubmiteprocessFlag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }

    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectLocation(String projectLocation) 
    {
        this.projectLocation = projectLocation;
    }

    public String getProjectLocation() 
    {
        return projectLocation;
    }
    public void setProjectLongLat(String projectLongLat) 
    {
        this.projectLongLat = projectLongLat;
    }

    public String getProjectLongLat() 
    {
        return projectLongLat;
    }
    public void setProjectManger(String projectManger) 
    {
        this.projectManger = projectManger;
    }

    public String getProjectManger() 
    {
        return projectManger;
    }
    public void setProjectPhone(String projectPhone) 
    {
        this.projectPhone = projectPhone;
    }

    public String getProjectPhone() 
    {
        return projectPhone;
    }
    public void setProjectBuilderName(String projectBuilderName) 
    {
        this.projectBuilderName = projectBuilderName;
    }

    public String getProjectBuilderName() 
    {
        return projectBuilderName;
    }
    public void setProjectBuilderId(Long projectBuilderId) 
    {
        this.projectBuilderId = projectBuilderId;
    }

    public Long getProjectBuilderId() 
    {
        return projectBuilderId;
    }
    public void setProjectTrafficStaffId(Long projectTrafficStaffId) 
    {
        this.projectTrafficStaffId = projectTrafficStaffId;
    }

    public Long getProjectTrafficStaffId() 
    {
        return projectTrafficStaffId;
    }
    public void setProjectFinishedFlag(String projectFinishedFlag) 
    {
        this.projectFinishedFlag = projectFinishedFlag;
    }

    public String getProjectFinishedFlag() 
    {
        return projectFinishedFlag;
    }
    public void setProjectDeleteFlag(String projectDeleteFlag) 
    {
        this.projectDeleteFlag = projectDeleteFlag;
    }

    public String getProjectDeleteFlag() 
    {
        return projectDeleteFlag;
    }
//    public void setProjectUpdateFlag(Date projectUpdateFlag)
//    {
//        this.projectUpdateFlag = projectUpdateFlag;
//    }
//
//    public Date getProjectUpdateFlag()
//    {
//        return projectUpdateFlag;
//    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("projectLocation", getProjectLocation())
            .append("projectLongLat", getProjectLongLat())
            .append("projectManger", getProjectManger())
            .append("projectPhone", getProjectPhone())
            .append("projectBuilderName", getProjectBuilderName())
            .append("projectBuilderId", getProjectBuilderId())
            .append("projectTrafficStaffId", getProjectTrafficStaffId())
            .append("projectFinishedFlag", getProjectFinishedFlag())
            .append("projectDeleteFlag", getProjectDeleteFlag())
            //.append("projectUpdateFlag", getProjectUpdateFlag())
            .toString();
    }
}
