package com.sju.program.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sju.program.domain.model.BaseUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 交管人员对象 traffice_staff
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@ApiModel(discriminator = "交管")
public class TrafficeStaff extends BaseUser
{
    private static final long serialVersionUID = 1L;

    /** 交管人员id */
    @ApiModelProperty(name = "trafficId",value = "交管人员id",example = "1")
    private Long trafficId;

    /** 用户账户 */
    @Excel(name = "用户账户")
    @ApiModelProperty(name = "trafficName",value = "用户账户",example = "traffic")
    private String trafficName;

    /** 密码 */
    @Excel(name = "密码")
    @ApiModelProperty(name = "trafficPassword",value = "密码")
    private String trafficPassword;

    /** 删除标识 */
    @Excel(name = "删除标识")
    @ApiModelProperty(name = "trafficDeleteFlag",value = "删除标识",example = "live | del")
    private String trafficDeleteFlag;

//    /** 更新时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date trafficUpdateFlag;

    private int authenticate;

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
        return  trafficPassword;
    }
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
//    public void setTrafficUpdateFlag(Date trafficUpdateFlag)
//    {
//        this.trafficUpdateFlag = trafficUpdateFlag;
//    }
//
//    public Date getTrafficUpdateFlag()
//    {
//        return trafficUpdateFlag;
//    }

    @Override
    public Long getId() {
        return trafficId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trafficId", getTrafficId())
            .append("trafficName", getTrafficName())
            .append("trafficPassword", getTrafficPassword())
            .append("trafficDeleteFlag", getTrafficDeleteFlag())
            //.append("trafficUpdateFlag", getTrafficUpdateFlag())
            .toString();
    }
}
