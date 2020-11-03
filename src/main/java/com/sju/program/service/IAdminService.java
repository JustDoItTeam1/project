package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.Admin;

/**
 * 管理员账户Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IAdminService 
{
    /**
     * 查询管理员账户
     * 
     * @param adminId 管理员账户ID
     * @return 管理员账户
     */
    public Admin selectAdminById(Long adminId);

    /**
     * 查询管理员账户列表
     * 
     * @param admin 管理员账户
     * @return 管理员账户集合
     */
    public List<Admin> selectAdminList(Admin admin);

    /**
     * 新增管理员账户
     * 
     * @param admin 管理员账户
     * @return 结果
     */
    public int insertAdmin(Admin admin);

    /**
     * 修改管理员账户
     * 
     * @param admin 管理员账户
     * @return 结果
     */
    public int updateAdmin(Admin admin);

    /**
     * 批量删除管理员账户
     * 
     * @param adminIds 需要删除的管理员账户ID
     * @return 结果
     */
    public int deleteAdminByIds(Long[] adminIds);

    /**
     * 删除管理员账户信息
     * 
     * @param adminId 管理员账户ID
     * @return 结果
     */
    public int deleteAdminById(Long adminId);
}
