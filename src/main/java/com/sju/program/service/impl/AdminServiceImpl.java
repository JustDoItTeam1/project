package com.sju.program.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.AdminMapper;
import com.sju.program.domain.Admin;
import com.sju.program.service.IAdminService;

/**
 * 管理员账户Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class AdminServiceImpl implements IAdminService 
{
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询管理员账户
     * 
     * @param adminId 管理员账户ID
     * @return 管理员账户
     */
    @Override
    public Admin selectAdminById(Long adminId)
    {
        return adminMapper.selectAdminById(adminId);
    }

    @Override
    public Admin selectAdminByUsername(String adminUsername) {
        return adminMapper.selectAdminByUsername(adminUsername);
    }

    /**
     * 查询管理员账户列表
     * 
     * @param admin 管理员账户
     * @return 管理员账户
     */
    @Override
    public List<Admin> selectAdminList(Admin admin)
    {
        return adminMapper.selectAdminList(admin);
    }

    /**
     * 新增管理员账户
     * 
     * @param admin 管理员账户
     * @return 结果
     */
    @Override
    public int insertAdmin(Admin admin)
    {
        return adminMapper.insertAdmin(admin);
    }

    /**
     * 修改管理员账户
     * 
     * @param admin 管理员账户
     * @return 结果
     */
    @Override
    public int updateAdmin(Admin admin)
    {
        return adminMapper.updateAdmin(admin);
    }

    /**
     * 批量删除管理员账户
     * 
     * @param adminIds 需要删除的管理员账户ID
     * @return 结果
     */
    @Override
    public int deleteAdminByIds(Long[] adminIds)
    {
        return adminMapper.deleteAdminByIds(adminIds);
    }

    /**
     * 删除管理员账户信息
     * 
     * @param adminId 管理员账户ID
     * @return 结果
     */
    @Override
    public int deleteAdminById(Long adminId)
    {
        return adminMapper.deleteAdminById(adminId);
    }
}
