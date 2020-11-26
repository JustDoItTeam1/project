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
 * 交警人员对象 police
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@ApiModel(description = "交警")
public class Police  extends BaseUser
{
    private static final long serialVersionUID = 1L;

    /** 交警人员id */
    @ApiModelProperty(name = "policeId",value = "交警人员id",example = "1")
    @ExcelIgnore
    private Long policeId;

    /** 用户账户 */
    @Excel(name = "用户账户")
    @ApiModelProperty(name = "policeName",value = "用户账户",example = "jj111111")
    @ExcelProperty("账户")
    private String policeName;

    /** 密码 */
    @Excel(name = "密码")
    @ApiModelProperty(name = "policePassword",value = "密码")
    @ExcelProperty("密码")
    private String policePassword;

    /** 删除标识 */
    @Excel(name = "删除标识")
    @ApiModelProperty(name = "policeDeleteFlag",value = "删除标识",example = "live | del")
    @ExcelProperty("删除标识")
    private String policeDeleteFlag;

//    /** 更新标识 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "更新标识", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date policeUpdateFlag;
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
        return  policePassword;
    }
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
//    public void setPoliceUpdateFlag(Date policeUpdateFlag)
//    {
//        this.policeUpdateFlag = policeUpdateFlag;
//    }
//
//    public Date getPoliceUpdateFlag()
//    {
//        return policeUpdateFlag;
//    }

    @Override
    public Long getId() {
        return policeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("policeId", getPoliceId())
            .append("policeName", getPoliceName())
            .append("policePassword", getPolicePassword())
            .append("policeDeleteFlag", getPoliceDeleteFlag())
            //.append("policeUpdateFlag", getPoliceUpdateFlag())
            .toString();
    }
}
