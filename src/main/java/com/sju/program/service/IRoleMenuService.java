package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.RoleMenu;

/**
 * 权限菜单Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IRoleMenuService 
{
    /**
     * 查询权限菜单
     * 
     * @param roleId 权限菜单ID
     * @return 权限菜单
     */
    public RoleMenu selectRoleMenuById(Long roleId);

    /**
     * 查询权限菜单列表
     * 
     * @param roleMenu 权限菜单
     * @return 权限菜单集合
     */
    public List<RoleMenu> selectRoleMenuList(RoleMenu roleMenu);

    /**
     * 新增权限菜单
     * 
     * @param roleMenu 权限菜单
     * @return 结果
     */
    public int insertRoleMenu(RoleMenu roleMenu);

    /**
     * 修改权限菜单
     * 
     * @param roleMenu 权限菜单
     * @return 结果
     */
    public int updateRoleMenu(RoleMenu roleMenu);

    /**
     * 批量删除权限菜单
     * 
     * @param roleIds 需要删除的权限菜单ID
     * @return 结果
     */
    public int deleteRoleMenuByIds(Long[] roleIds);

    /**
     * 删除权限菜单信息
     * 
     * @param roleId 权限菜单ID
     * @return 结果
     */
    public int deleteRoleMenuById(Long roleId);
}
