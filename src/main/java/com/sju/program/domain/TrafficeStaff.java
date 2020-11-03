package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 交管人员对象 traffice_staff
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class TrafficeStaff extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 交管人员id */
    private Long trafficId;

    /** 用户账户 */
    @Excel(name = "用户账户")
    private String trafficName;

    /** 密码 */
    @Excel(name = "密码")
    private String trafficPassword;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String trafficDeleteFlag;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trafficUpdateFlag;

    public void setTrafficId(Long trafficId) 
    {
        this.trafficId = trafficId;
    }

    public Long getTrafficId() 
    {
        return trafficId;
    }
    public void setTrafficName(String trafficName) 
    {
        this.trafficName = trafficName;
    }

    public String getTrafficName() 
    {
        return trafficName;
    }
    public void setTrafficPassword(String trafficPassword) 
    {
        this.trafficPassword = trafficPassword;
    }

    public String getTrafficPassword() 
    {
        return trafficPassword;
    }
    public void setTrafficDeleteFlag(String trafficDeleteFlag) 
    {
        this.trafficDeleteFlag = trafficDeleteFlag;
    }

    public String getTrafficDeleteFlag() 
    {
        return trafficDeleteFlag;
    }
    public void setTrafficUpdateFlag(Date trafficUpdateFlag) 
    {
        this.trafficUpdateFlag = trafficUpdateFlag;
    }

    public Date getTrafficUpdateFlag() 
    {
        return trafficUpdateFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trafficId", getTrafficId())
            .append("trafficName", getTrafficName())
            .append("trafficPassword", getTrafficPassword())
            .append("trafficDeleteFlag", getTrafficDeleteFlag())
            .append("trafficUpdateFlag", getTrafficUpdateFlag())
            .toString();
    }
}
