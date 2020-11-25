package com.sju.program.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sju.program.domain.model.BaseUser;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;
import com.sju.program.domain.BaseEntity;

/**
 * 管理员账户对象 admin
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@ApiModel(description = "管理员")
public class Admin extends BaseUser
{
    private static final long serialVersionUID = 1L;

    /** 账号 */
    private Long adminId;

    /** 用户账户 */
    @Excel(name = "用户账户")
    private String adminUsername;

    /** 密码 */
    @Excel(name = "密码")
    private String adminPassword;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private String adminDeleteFlag;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adminUpdateFlag;
    /**权限标识**/
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
        return  adminPassword;
    }
    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }

    public Long getAdminId() 
    {
        return adminId;
    }
    public void setAdminUsername(String adminUsername) 
    {
        this.adminUsername = adminUsername;
    }

    public String getAdminUsername() 
    {
        return adminUsername;
    }
    public void setAdminPassword(String adminPassword) 
    {
        this.adminPassword = adminPassword;
    }

    public String getAdminPassword()
    {
        return adminPassword;
    }
    public void setAdminDeleteFlag(String adminDeleteFlag) 
    {
        this.adminDeleteFlag = adminDeleteFlag;
    }

    public String getAdminDeleteFlag() 
    {
        return adminDeleteFlag;
    }
    public void setAdminUpdateFlag(Date adminUpdateFlag) 
    {
        this.adminUpdateFlag = adminUpdateFlag;
    }

    public Date getAdminUpdateFlag() 
    {
        return adminUpdateFlag;
    }
    @Override
    public Long getId() {
        return adminId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adminId", getAdminId())
            .append("adminUsername", getAdminUsername())
            .append("adminPassword", getAdminPassword())
            .append("adminDeleteFlag", getAdminDeleteFlag())
            .append("adminUpdateFlag", getAdminUpdateFlag())
            .toString();
    }
}
