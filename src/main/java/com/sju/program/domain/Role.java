package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 权限对象 role
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class Role extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 权限id */
    private Long roleId;

    /** 权限名称 */
    @Excel(name = "权限名称")
    private String roleName;

    /** 权限删除标志 */
    @Excel(name = "权限删除标志")
    private String roleDeleteFlag;

    /** 权限更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "权限更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date roleUpdateFlag;

    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }
    public void setRoleDeleteFlag(String roleDeleteFlag) 
    {
        this.roleDeleteFlag = roleDeleteFlag;
    }

    public String getRoleDeleteFlag() 
    {
        return roleDeleteFlag;
    }
    public void setRoleUpdateFlag(Date roleUpdateFlag) 
    {
        this.roleUpdateFlag = roleUpdateFlag;
    }

    public Date getRoleUpdateFlag() 
    {
        return roleUpdateFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("roleDeleteFlag", getRoleDeleteFlag())
            .append("roleUpdateFlag", getRoleUpdateFlag())
            .toString();
    }
}
