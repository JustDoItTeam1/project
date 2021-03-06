package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 交警人员对象 police
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class Police extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 交警人员id */
    private Long policeId;

    /** 用户账户 */
    @Excel(name = "用户账户")
    private String policeName;

    /** 密码 */
    @Excel(name = "密码")
    private String policePassword;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String policeDeleteFlag;

    /** 更新标识 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新标识", width = 30, dateFormat = "yyyy-MM-dd")
    private Date policeUpdateFlag;

    public void setPoliceId(Long policeId) 
    {
        this.policeId = policeId;
    }

    public Long getPoliceId() 
    {
        return policeId;
    }
    public void setPoliceName(String policeName) 
    {
        this.policeName = policeName;
    }

    public String getPoliceName() 
    {
        return policeName;
    }
    public void setPolicePassword(String policePassword) 
    {
        this.policePassword = policePassword;
    }

    public String getPolicePassword() 
    {
        return policePassword;
    }
    public void setPoliceDeleteFlag(String policeDeleteFlag) 
    {
        this.policeDeleteFlag = policeDeleteFlag;
    }

    public String getPoliceDeleteFlag() 
    {
        return policeDeleteFlag;
    }
    public void setPoliceUpdateFlag(Date policeUpdateFlag) 
    {
        this.policeUpdateFlag = policeUpdateFlag;
    }

    public Date getPoliceUpdateFlag() 
    {
        return policeUpdateFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("policeId", getPoliceId())
            .append("policeName", getPoliceName())
            .append("policePassword", getPolicePassword())
            .append("policeDeleteFlag", getPoliceDeleteFlag())
            .append("policeUpdateFlag", getPoliceUpdateFlag())
            .toString();
    }
}
