package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sju.program.domain.model.BaseUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 施工单位对象 builder
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class Builder extends BaseUser
{
    private static final long serialVersionUID = 1L;

    /** 施工单位id */
    private Long builderId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String builderUsername;

    /** 施工单位名称 */
    @Excel(name = "施工单位名称")
    private String builderName;

    /** 密码 */
    @Excel(name = "密码")
    private String builderPassword;

    /** 施工单位法人 */
    @Excel(name = "施工单位法人")
    private String builderCorporate;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String builderPhone;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String builderAddress;

    /** 企业编号 */
    @Excel(name = "企业编号")
    private String builderEnterpriseNumber;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String builderDeleteFlag;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date builderUpdateFlag;

    private int authenticate;

    public int getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(int authenticate) {
        this.authenticate = authenticate;
    }

    @Override
    public String getUserPassword(){
        return  builderPassword;
    }
    public void setBuilderId(Long builderId) 
    {
        this.builderId = builderId;
    }

    public Long getBuilderId() 
    {
        return builderId;
    }
    public void setBuilderUsername(String builderUsername) 
    {
        this.builderUsername = builderUsername;
    }

    public String getBuilderUsername() 
    {
        return builderUsername;
    }
    public void setBuilderName(String builderName) 
    {
        this.builderName = builderName;
    }

    public String getBuilderName() 
    {
        return builderName;
    }
    public void setBuilderPassword(String builderPassword) 
    {
        this.builderPassword = builderPassword;
    }

    public String getBuilderPassword() 
    {
        return builderPassword;
    }
    public void setBuilderCorporate(String builderCorporate) 
    {
        this.builderCorporate = builderCorporate;
    }

    public String getBuilderCorporate() 
    {
        return builderCorporate;
    }
    public void setBuilderPhone(String builderPhone) 
    {
        this.builderPhone = builderPhone;
    }

    public String getBuilderPhone() 
    {
        return builderPhone;
    }
    public void setBuilderAddress(String builderAddress) 
    {
        this.builderAddress = builderAddress;
    }

    public String getBuilderAddress() 
    {
        return builderAddress;
    }
    public void setBuilderEnterpriseNumber(String builderEnterpriseNumber) 
    {
        this.builderEnterpriseNumber = builderEnterpriseNumber;
    }

    public String getBuilderEnterpriseNumber() 
    {
        return builderEnterpriseNumber;
    }
    public void setBuilderDeleteFlag(String builderDeleteFlag) 
    {
        this.builderDeleteFlag = builderDeleteFlag;
    }

    public String getBuilderDeleteFlag() 
    {
        return builderDeleteFlag;
    }
    public void setBuilderUpdateFlag(Date builderUpdateFlag) 
    {
        this.builderUpdateFlag = builderUpdateFlag;
    }

    public Date getBuilderUpdateFlag() 
    {
        return builderUpdateFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("builderId", getBuilderId())
            .append("builderUsername", getBuilderUsername())
            .append("builderName", getBuilderName())
            .append("builderPassword", getBuilderPassword())
            .append("builderCorporate", getBuilderCorporate())
            .append("builderPhone", getBuilderPhone())
            .append("builderAddress", getBuilderAddress())
            .append("builderEnterpriseNumber", getBuilderEnterpriseNumber())
            .append("builderDeleteFlag", getBuilderDeleteFlag())
            .append("builderUpdateFlag", getBuilderUpdateFlag())
            .toString();
    }
}
