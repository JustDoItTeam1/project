package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 围蔽方案对象 siege_scheme
 *
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@ApiModel(description = "围蔽方案")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiegeScheme
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "ssId",value = "围蔽id",example = "1",required = true)
    /** 围蔽方案id */
    private Long ssId;

    /** 围蔽状态 */
    @ApiModelProperty(name = "ssStatus",value = "围蔽状态")
    @Excel(name = "围蔽状态")
    private String ssStatus;

    /** 围蔽车道 */
    @Excel(name = "围蔽车道")
    @ApiModelProperty(name = "ssLane",value = "围蔽车道" ,example = "二")
    private String ssLane;

    /** 围蔽阶段 */
    @Excel(name = "围蔽阶段")
    @ApiModelProperty(name="ssStage",value = "围蔽阶段",example = "1")
    private String ssStage;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(name = "ssStartTime",value = "开始时间",example = "2020-11-05")
    private Date ssStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(name = "ssEndTime",value = "结束时间",example = "2020-11-07")
    private Date ssEndTime;

    /** 围蔽区域(地图) */
    @Excel(name = "围蔽区域(地图)")
    @ApiModelProperty(name = "ssRange",value = "围蔽区域（地图）",example = "103.98704041455076,30.764638942180408")
    private String ssRange;

    /** 项目id */
    @Excel(name = "项目id")
    @ApiModelProperty(name = "ssProjectId",value = "项目Id",example = "1")
    private Long ssProjectId;

    /** 围蔽性质 */
    @Excel(name = "围蔽性质")
    @ApiModelProperty(name = "ssProperties",value = "围蔽性质",example = "半围蔽")
    private String ssProperties;

    /** 围蔽拒绝建议 */
    @Excel(name = "围蔽拒绝建议")
    @ApiModelProperty(name = "ssSuggessions",value = "围蔽建议",example = "未设安全标志")
    private String ssSuggessions;

    /** 围蔽文件路径 */
    @Excel(name = "围蔽文件路径")
    @ApiModelProperty(name = "ssFilePath",value = "围蔽文件路径",example = "C:\\Users\\Administrator\\Desktop\\test.docx")
    private String ssFilePath;

    /** 施工单位id */
    @Excel(name = "施工单位id")
    @ApiModelProperty(name = "ssBuilderId",value = "施工单位id",example = "1")
    private Long ssBuilderId;

    /** 审核交管人员id */
    @Excel(name = "审核交管人员id")
    @ApiModelProperty(name = "ssTrafficStaffId",value = "审核交管人员id",example = "1")
    private Long ssTrafficStaffId;

    /** 删除标识 */
    @Excel(name = "删除标识")
    @ApiModelProperty(name = "ssDeleteFlag",value = "删除标识",example = "live|del")
    private String ssDeleteFlag;

    /** 审核标识 */
    @Excel(name = "审核标识")
    @ApiModelProperty(name = "ssVerifyFlag",value = "审核标识",example = "pass|nopass|review")
    private String ssVerifyFlag;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(name = "ssVerifyDate",value = "审核时间",example = "2020-11-07")
    private Date ssVerifyDate;

    private boolean ss=false;

    private boolean ssmap=true;

    public boolean isSs() {
        return ss;
    }

    public void setSs(boolean ss) {
        this.ss = ss;
    }

    public boolean isSsmap() {
        return ssmap;
    }

    public void setSsmap(boolean ssmap) {
        this.ssmap = ssmap;
    }

    //    /** 更新时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
//    @ApiModelProperty(name = "ssUpdateFlag",value = "更新时间",example = "2020-11-07")
//    private Date ssUpdateFlag;

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
//    public void setSsUpdateFlag(Date ssUpdateFlag)
//    {
//        this.ssUpdateFlag = ssUpdateFlag;
//    }
//
//    public Date getSsUpdateFlag()
//    {
//        return ssUpdateFlag;
//    }

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
            //.append("ssUpdateFlag", getSsUpdateFlag())
            .toString();
    }
}
