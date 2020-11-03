package com.sju.program.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.RoleMenuMapper;
import com.sju.program.domain.RoleMenu;
import com.sju.program.service.IRoleMenuService;

/**
 * 权限菜单Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class RoleMenuServiceImpl implements IRoleMenuService 
{
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询权限菜单
     * 
     * @param roleId 权限菜单ID
     * @return 权限菜单
     */
    @Override
    public RoleMenu selectRoleMenuById(Long roleId)
    {
        return roleMenuMapper.selectRoleMenuById(roleId);
    }

    /**
     * 查询权限菜单列表
     * 
     * @param roleMenu 权限菜单
     * @return 权限菜单
     */
    @Override
    public List<RoleMenu> selectRoleMenuList(RoleMenu roleMenu)
    {
        return roleMenuMapper.selectRoleMenuList(roleMenu);
    }

    /**
     * 新增权限菜单
     * 
     * @param roleMenu 权限菜单
     * @return 结果
     */
    @Override
    public int insertRoleMenu(RoleMenu roleMenu)
    {
        return roleMenuMapper.insertRoleMenu(roleMenu);
    }

    /**
     * 修改权限菜单
     * 
     * @param roleMenu 权限菜单
     * @return 结果
     */
    @Override
    public int updateRoleMenu(RoleMenu roleMenu)
    {
        return roleMenuMapper.updateRoleMenu(roleMenu);
    }

    /**
     * 批量删除权限菜单
     * 
     * @param roleIds 需要删除的权限菜单ID
     * @return 结果
     */
    @Override
    public int deleteRoleMenuByIds(Long[] roleIds)
    {
        return roleMenuMapper.deleteRoleMenuByIds(roleIds);
    }

    /**
     * 删除权限菜单信息
     * 
     * @param roleId 权限菜单ID
     * @return 结果
     */
    @Override
    public int deleteRoleMenuById(Long roleId)
    {
        return roleMenuMapper.deleteRoleMenuById(roleId);
    }
}
