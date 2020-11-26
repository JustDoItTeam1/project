package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 整改信息对象 rectification_info
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class RectificationInfo
{
    private static final long serialVersionUID = 1L;

    /** 整改id */
    private Long riId;

    /** 项目id */
    @Excel(name = "项目id")
    private Long riProjectId;

    /** 整改截至时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "整改截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date riDate;

    /** 整改需求 */
    @Excel(name = "整改需求")
    private String riRequirements;

    /** 提交交警id */
    @Excel(name = "提交交警id")
    private Long riPoliceId;

    /** 现场照片地址 */
    @Excel(name = "现场照片地址")
    private String riPhotoPath;

    /** 整改状态 */
    @Excel(name = "整改状态")
    private String riStatus;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String riDeleteFlag;

//    /** 更新时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date riUpdateFlag;

    public void setRiId(Long riId) 
    {
        this.riId = riId;
    }

    public Long getRiId() 
    {
        return riId;
    }
    public void setRiProjectId(Long riProjectId) 
    {
        this.riProjectId = riProjectId;
    }

    public Long getRiProjectId() 
    {
        return riProjectId;
    }
    public void setRiDate(Date riDate) 
    {
        this.riDate = riDate;
    }

    public Date getRiDate() 
    {
        return riDate;
    }
    public void setRiRequirements(String riRequirements) 
    {
        this.riRequirements = riRequirements;
    }

    public String getRiRequirements() 
    {
        return riRequirements;
    }
    public void setRiPoliceId(Long riPoliceId) 
    {
        this.riPoliceId = riPoliceId;
    }

    public Long getRiPoliceId() 
    {
        return riPoliceId;
    }
    public void setRiPhotoPath(String riPhotoPath) 
    {
        this.riPhotoPath = riPhotoPath;
    }

    public String getRiPhotoPath() 
    {
        return riPhotoPath;
    }
    public void setRiStatus(String riStatus) 
    {
        this.riStatus = riStatus;
    }

    public String getRiStatus() 
    {
        return riStatus;
    }
    public void setRiDeleteFlag(String riDeleteFlag) 
    {
        this.riDeleteFlag = riDeleteFlag;
    }

    public String getRiDeleteFlag() 
    {
        return riDeleteFlag;
    }
//    public void setRiUpdateFlag(Date riUpdateFlag)
//    {
//        this.riUpdateFlag = riUpdateFlag;
//    }
//
//    public Date getRiUpdateFlag()
//    {
//        return riUpdateFlag;
//    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("riId", getRiId())
            .append("riProjectId", getRiProjectId())
            .append("riDate", getRiDate())
            .append("riRequirements", getRiRequirements())
            .append("riPoliceId", getRiPoliceId())
            .append("riPhotoPath", getRiPhotoPath())
            .append("riStatus", getRiStatus())
            .append("riDeleteFlag", getRiDeleteFlag())
            //.append("riUpdateFlag", getRiUpdateFlag())
            .toString();
    }
}
