package com.sju.program.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sju.program.domain.model.BaseUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 施工单位对象 builder
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@ApiModel(description = "施工单位")
public class Builder extends BaseUser
{
    private static final long serialVersionUID = 1L;

    /** 施工单位id */
    @ApiModelProperty(name ="builderId",value = "施工单位id",example = "1")
    @ExcelIgnore
    private Long builderId;

    /** 用户账号 */
    //@Excel(name = "用户账号")
    @ApiModelProperty(name ="builderUsername",value = "用户账号",example = "builder",required = true)
    @ExcelProperty("用户账号")
    private String builderUsername;

    /** 施工单位名称 */
    //@Excel(name = "施工单位名称")
    @ApiModelProperty(name ="builderName",value = "施工单位名称",example = "中铁八局")
    @ExcelProperty("施工单位名称")
    private String builderName;

    /** 密码 */
    //@Excel(name = "密码")
    @ApiModelProperty(name ="builderPassword",value = "密码")
    @ExcelProperty("密码")
    private String builderPassword;

    /** 施工单位法人 */
    //@Excel(name = "施工单位法人")
    @ApiModelProperty(name ="builderCorporate",value = "施工单位法人",example = "张三")
    @ExcelProperty("施工单位法人")
    private String builderCorporate;

    /** 联系电话 */
    //@Excel(name = "联系电话")
    @ApiModelProperty(name ="builderPhone",value = "联系电话",example = "15255554444")
    @ExcelProperty("联系电话")
    private String builderPhone;

    /** 公司地址 */
   // @Excel(name = "公司地址")
    @ApiModelProperty(name ="builderAddress",value = "公司地址",example = "成都天府新区腾讯大厦")
    @ExcelProperty("公司地址")
    private String builderAddress;

    /** 企业编号 */
    //@Excel(name = "企业编号")
    @ApiModelProperty(name ="builderEnterpriseNumber",value = "企业编号",example = "xxxxxxxxxx")
    @ExcelProperty("企业编号")
    private String builderEnterpriseNumber;

    /** 删除标识 */
    //@Excel(name = "删除标识")
    @ApiModelProperty(name ="builderDeleteFlag",value = "删除标识",example = "live | del")
    @ExcelProperty("删除标识")
    private String builderDeleteFlag;

    @ExcelIgnore
    private int authenticate;

    @ExcelIgnore
    private ArrayList<String> permissions;

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public int getAuthenticate() {
        return authenticate;
    }
    @Override
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
//    public void setBuilderUpdateFlag(Date builderUpdateFlag)
//    {
//        this.builderUpdateFlag = builderUpdateFlag;
//    }
//
//    public Date getBuilderUpdateFlag()
//    {
//        return builderUpdateFlag;
//    }

    @Override
    public Long getId() {
        return builderId;
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
            //.append("builderUpdateFlag", getBuilderUpdateFlag())
            .toString();
    }
}
