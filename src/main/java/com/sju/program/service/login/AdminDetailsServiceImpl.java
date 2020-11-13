package com.sju.program.service.login;

import com.sju.program.domain.Admin;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.enums.UserStatus;
import com.sju.program.exception.BaseException;
import com.sju.program.utils.StringUtils;
import com.sju.program.service.IAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service("AdminDetailsServiceImpl")
public class AdminDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(AdminDetailsServiceImpl.class);

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Admin admin = iAdminService.selectAdminByUsername(username);
        //Admin admin =null;
        if (StringUtils.isNull(admin))
        {
            System.out.println("admin"+admin);
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
//        else if (UserStatus.DELETED.getCode().equals(admin.getDelFlag()))
//        {
//            log.info("登录用户：{} 已被删除.", username);
//            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
//        }
//        else if (UserStatus.DISABLE.getCode().equals(admin.getStatus()))
//        {
//            log.info("登录用户：{} 已被停用.", username);
//            throw new BaseException("对不起，您的账号：" + username + " 已停用");
//        }

        return createLoginUser(admin);
    }

    public UserDetails createLoginUser(Admin user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(1));
    }
}
