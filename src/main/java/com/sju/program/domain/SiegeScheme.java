package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 围蔽方案对象 siege_scheme
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class SiegeScheme
{
    private static final long serialVersionUID = 1L;

    /** 围蔽方案id */
    private Long ssId;

    /** 围蔽状态 */
    @Excel(name = "围蔽状态")
    private String ssStatus;

    /** 围蔽车道 */
    @Excel(name = "围蔽车道")
    private String ssLane;

    /** 围蔽阶段 */
    @Excel(name = "围蔽阶段")
    private String ssStage;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ssStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ssEndTime;

    /** 围蔽区域(地图) */
    @Excel(name = "围蔽区域(地图)")
    private String ssRange;

    /** 项目id */
    @Excel(name = "项目id")
    private Long ssProjectId;

    /** 围蔽性质 */
    @Excel(name = "围蔽性质")
    private String ssProperties;

    /** 围蔽拒绝建议 */
    @Excel(name = "围蔽拒绝建议")
    private String ssSuggessions;

    /** 围蔽文件路径 */
    @Excel(name = "围蔽文件路径")
    private String ssFilePath;

    /** 施工单位id */
    @Excel(name = "施工单位id")
    private Long ssBuilderId;

    /** 审核交管人员id */
    @Excel(name = "审核交管人员id")
    private Long ssTrafficStaffId;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String ssDeleteFlag;

    /** 审核标识 */
    @Excel(name = "审核标识")
    private String ssVerifyFlag;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ssVerifyDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ssUpdateFlag;

    public void setSsId(Long ssId) 
    {
        this.ssId = ssId;
    }

    public Long getSsId() 
    {
        return ssId;
    }
    public void setSsStatus(String ssStatus) 
    {
        this.ssStatus = ssStatus;
    }

    public String getSsStatus() 
    {
        return ssStatus;
    }
    public void setSsLane(String ssLane) 
    {
        this.ssLane = ssLane;
    }

    public String getSsLane() 
    {
        return ssLane;
    }
    public void setSsStage(String ssStage) 
    {
        this.ssStage = ssStage;
    }

    public String getSsStage() 
    {
        return ssStage;
    }
    public void setSsStartTime(Date ssStartTime) 
    {
        this.ssStartTime = ssStartTime;
    }

    public Date getSsStartTime() 
    {
        return ssStartTime;
    }
    public void setSsEndTime(Date ssEndTime) 
    {
        this.ssEndTime = ssEndTime;
    }

    public Date getSsEndTime() 
    {
        return ssEndTime;
    }
    public void setSsRange(String ssRange) 
    {
        this.ssRange = ssRange;
    }

    public String getSsRange() 
    {
        return ssRange;
    }
    public void setSsProjectId(Long ssProjectId) 
    {
        this.ssProjectId = ssProjectId;
    }

    public Long getSsProjectId() 
    {
        return ssProjectId;
    }
    public void setSsProperties(String ssProperties) 
    {
        this.ssProperties = ssProperties;
    }

    public String getSsProperties() 
    {
        return ssProperties;
    }
    public void setSsSuggessions(String ssSuggessions) 
    {
        this.ssSuggessions = ssSuggessions;
    }

    public String getSsSuggessions() 
    {
        return ssSuggessions;
    }
    public void setSsFilePath(String ssFilePath) 
    {
        this.ssFilePath = ssFilePath;
    }

    public String getSsFilePath() 
    {
        return ssFilePath;
    }
    public void setSsBuilderId(Long ssBuilderId) 
    {
        this.ssBuilderId = ssBuilderId;
    }

    public Long getSsBuilderId() 
    {
        return ssBuilderId;
    }
    public void setSsTrafficStaffId(Long ssTrafficStaffId) 
    {
        this.ssTrafficStaffId = ssTrafficStaffId;
    }

    public Long getSsTrafficStaffId() 
    {
        return ssTrafficStaffId;
    }
    public void setSsDeleteFlag(String ssDeleteFlag) 
    {
        this.ssDeleteFlag = ssDeleteFlag;
    }

    public String getSsDeleteFlag() 
    {
        return ssDeleteFlag;
    }
    public void setSsVerifyFlag(String ssVerifyFlag) 
    {
        this.ssVerifyFlag = ssVerifyFlag;
    }

    public String getSsVerifyFlag() 
    {
        return ssVerifyFlag;
    }
    public void setSsVerifyDate(Date ssVerifyDate) 
    {
        this.ssVerifyDate = ssVerifyDate;
    }

    public Date getSsVerifyDate() 
    {
        return ssVerifyDate;
    }
    public void setSsUpdateFlag(Date ssUpdateFlag) 
    {
        this.ssUpdateFlag = ssUpdateFlag;
    }

    public Date getSsUpdateFlag() 
    {
        return ssUpdateFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ssId", getSsId())
            .append("ssStatus", getSsStatus())
            .append("ssLane", getSsLane())
            .append("ssStage", getSsStage())
            .append("ssStartTime", getSsStartTime())
            .append("ssEndTime", getSsEndTime())
            .append("ssRange", getSsRange())
            .append("ssProjectId", getSsProjectId())
            .append("ssProperties", getSsProperties())
            .append("ssSuggessions", getSsSuggessions())
            .append("ssFilePath", getSsFilePath())
            .append("ssBuilderId", getSsBuilderId())
            .append("ssTrafficStaffId", getSsTrafficStaffId())
            .append("ssDeleteFlag", getSsDeleteFlag())
            .append("ssVerifyFlag", getSsVerifyFlag())
            .append("ssVerifyDate", getSsVerifyDate())
            .append("ssUpdateFlag", getSsUpdateFlag())
            .toString();
    }
}
