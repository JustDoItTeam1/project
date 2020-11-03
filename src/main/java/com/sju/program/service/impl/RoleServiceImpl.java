package com.sju.program.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.RoleMapper;
import com.sju.program.domain.Role;
import com.sju.program.service.IRoleService;

/**
 * 权限Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class RoleServiceImpl implements IRoleService 
{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询权限
     * 
     * @param roleId 权限ID
     * @return 权限
     */
    @Override
    public Role selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 查询权限列表
     * 
     * @param role 权限
     * @return 权限
     */
    @Override
    public List<Role> selectRoleList(Role role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 新增权限
     * 
     * @param role 权限
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        return roleMapper.insertRole(role);
    }

    /**
     * 修改权限
     * 
     * @param role 权限
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * 批量删除权限
     * 
     * @param roleIds 需要删除的权限ID
     * @return 结果
     */
    @Override
    public int deleteRoleByIds(Long[] roleIds)
    {
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 删除权限信息
     * 
     * @param roleId 权限ID
     * @return 结果
     */
    @Override
    public int deleteRoleById(Long roleId)
    {
        return roleMapper.deleteRoleById(roleId);
    }
}
