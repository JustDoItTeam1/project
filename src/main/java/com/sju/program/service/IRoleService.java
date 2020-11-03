package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.Role;

/**
 * 权限Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IRoleService 
{
    /**
     * 查询权限
     * 
     * @param roleId 权限ID
     * @return 权限
     */
    public Role selectRoleById(Long roleId);

    /**
     * 查询权限列表
     * 
     * @param role 权限
     * @return 权限集合
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 新增权限
     * 
     * @param role 权限
     * @return 结果
     */
    public int insertRole(Role role);

    /**
     * 修改权限
     * 
     * @param role 权限
     * @return 结果
     */
    public int updateRole(Role role);

    /**
     * 批量删除权限
     * 
     * @param roleIds 需要删除的权限ID
     * @return 结果
     */
    public int deleteRoleByIds(Long[] roleIds);

    /**
     * 删除权限信息
     * 
     * @param roleId 权限ID
     * @return 结果
     */
    public int deleteRoleById(Long roleId);
}
