package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 施工进度对象 project_process
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class ProjectProcess
{
    private static final long serialVersionUID = 1L;

    /** 施工进度id */
    private Long ppId;

    /** 填写时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "填写时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ppTime;

    /** 项目id */
    @Excel(name = "项目id")
    private Long ppProjectId;

    /** 施工进度描述 */
    @Excel(name = "施工进度描述")
    private String ppDescription;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String ppDeleteFlag;


    public void setPpId(Long ppId) 
    {
        this.ppId = ppId;
    }

    public Long getPpId() 
    {
        return ppId;
    }
    public void setPpTime(Date ppTime) 
    {
        this.ppTime = ppTime;
    }

    public Date getPpTime() 
    {
        return ppTime;
    }
    public void setPpProjectId(Long ppProjectId) 
    {
        this.ppProjectId = ppProjectId;
    }

    public Long getPpProjectId() 
    {
        return ppProjectId;
    }
    public void setPpDescription(String ppDescription) 
    {
        this.ppDescription = ppDescription;
    }

    public String getPpDescription() 
    {
        return ppDescription;
    }
    public void setPpDeleteFlag(String ppDeleteFlag) 
    {
        this.ppDeleteFlag = ppDeleteFlag;
    }

    public String getPpDeleteFlag() 
    {
        return ppDeleteFlag;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ppId", getPpId())
            .append("ppTime", getPpTime())
            .append("ppProjectId", getPpProjectId())
            .append("ppDescription", getPpDescription())
            .append("ppDeleteFlag", getPpDeleteFlag())
            .toString();
    }
}
