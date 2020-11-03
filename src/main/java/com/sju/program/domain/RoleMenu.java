package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;

/**
 * 权限菜单对象 role_menu
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public class RoleMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 权限id */
    private Long roleId;

    /** 菜单id */
    private Long menuId;

    /** 删除标识 */
    @Excel(name = "删除标识")
    private String roleDeleteFlag;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date roleUpdateFlag;

    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setMenuId(Long menuId) 
    {
        this.menuId = menuId;
    }

    public Long getMenuId() 
    {
        return menuId;
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
            .append("menuId", getMenuId())
            .append("roleDeleteFlag", getRoleDeleteFlag())
            .append("roleUpdateFlag", getRoleUpdateFlag())
            .toString();
    }
}
