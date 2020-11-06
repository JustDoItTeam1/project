package com.sju.program.service.login;

import com.sju.program.domain.Builder;
import com.sju.program.domain.TrafficeStaff;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.service.IBuilderService;
import com.sju.program.service.ITrafficeStaffService;
import com.sju.program.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("TrafficStaffDetailsServiceImpl")
public class TrafficStaffDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(AdminDetailsServiceImpl.class);

    @Autowired
    private ITrafficeStaffService iTrafficeStaffService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        //Admin admin = iAdminService.selectUserByUserName(username);
        TrafficeStaff trafficeStaff =iTrafficeStaffService.selectTrafficeStaffByUsername(username);
        if (StringUtils.isNull(trafficeStaff))
        {
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

        return createLoginUser(trafficeStaff);
    }

    public UserDetails createLoginUser(TrafficeStaff trafficeStaff)
    {
        return new LoginUser(trafficeStaff, permissionService.getMenuPermission(3));
    }
}
