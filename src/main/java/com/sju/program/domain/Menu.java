package com.sju.program.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.sju.program.annotation.Excel;
/**
 * 菜单对象 menu
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@ApiModel(description = "菜单")
public class Menu
{
    private static final long serialVersionUID = 1L;

    /** 菜单id */
    private Long menuId;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String menuName;

    /** 父菜单id */
    @Excel(name = "父菜单id")
    private Long menuParentId;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long menuOrderNum;

    /** 路由地址 */
    @Excel(name = "路由地址")
    private String menuPath;

    /** 组件地址 */
    @Excel(name = "组件地址")
    private String menuComponent;

    /** 菜单类型 */
    @Excel(name = "菜单类型")
    private String menuType;

    /** 权限标识 */
    @Excel(name = "权限标识")
    private String menuPerms;

    /** 菜单图标 */
    @Excel(name = "菜单图标")
    private String menuIcon;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private String menuDeleteFlag;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date menuUpdateFlag;

    public void setMenuId(Long menuId) 
    {
        this.menuId = menuId;
    }

    public Long getMenuId() 
    {
        return menuId;
    }
    public void setMenuName(String menuName) 
    {
        this.menuName = menuName;
    }

    public String getMenuName() 
    {
        return menuName;
    }
    public void setMenuParentId(Long menuParentId) 
    {
        this.menuParentId = menuParentId;
    }

    public Long getMenuParentId() 
    {
        return menuParentId;
    }
    public void setMenuOrderNum(Long menuOrderNum) 
    {
        this.menuOrderNum = menuOrderNum;
    }

    public Long getMenuOrderNum() 
    {
        return menuOrderNum;
    }
    public void setMenuPath(String menuPath) 
    {
        this.menuPath = menuPath;
    }

    public String getMenuPath() 
    {
        return menuPath;
    }
    public void setMenuComponent(String menuComponent) 
    {
        this.menuComponent = menuComponent;
    }

    public String getMenuComponent() 
    {
        return menuComponent;
    }
    public void setMenuType(String menuType) 
    {
        this.menuType = menuType;
    }

    public String getMenuType() 
    {
        return menuType;
    }
    public void setMenuPerms(String menuPerms) 
    {
        this.menuPerms = menuPerms;
    }

    public String getMenuPerms() 
    {
        return menuPerms;
    }
    public void setMenuIcon(String menuIcon) 
    {
        this.menuIcon = menuIcon;
    }

    public String getMenuIcon() 
    {
        return menuIcon;
    }
    public void setMenuDeleteFlag(String menuDeleteFlag) 
    {
        this.menuDeleteFlag = menuDeleteFlag;
    }

    public String getMenuDeleteFlag() 
    {
        return menuDeleteFlag;
    }
    public void setMenuUpdateFlag(Date menuUpdateFlag) 
    {
        this.menuUpdateFlag = menuUpdateFlag;
    }

    public Date getMenuUpdateFlag() 
    {
        return menuUpdateFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("menuId", getMenuId())
            .append("menuName", getMenuName())
            .append("menuParentId", getMenuParentId())
            .append("menuOrderNum", getMenuOrderNum())
            .append("menuPath", getMenuPath())
            .append("menuComponent", getMenuComponent())
            .append("menuType", getMenuType())
            .append("menuPerms", getMenuPerms())
            .append("menuIcon", getMenuIcon())
            .append("menuDeleteFlag", getMenuDeleteFlag())
            .append("menuUpdateFlag", getMenuUpdateFlag())
            .toString();
    }
}
