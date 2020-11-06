package com.sju.program.service.login;

import com.sju.program.domain.Admin;
import com.sju.program.service.IMenuService;
import com.sju.program.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 * 
 * @author ruoyi
 */
@Component
public class SysPermissionService {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(Object user) {
        Set<String> roles = new HashSet<String>();
        //管理员拥有所有权限
//        if (user.isAdmin())
//        {
//            roles.add("admin");
//        }
//        else
//        {
//            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
//        }
        return roles;
    }

        /**
         * 获取菜单数据权限
         *
         * @param user 用户信息
         * @return 菜单权限信息
         */
        public ArrayList<String> getMenuPermission(int auth)
        {
            ArrayList<String> perms = new ArrayList<>();
            //管理员拥有所有权限
                if (auth==1) {
                    perms.add("*:*:*");
                } else {
                   perms.addAll(menuService.selectMenuPermsByAuthenticate(auth));
                }
            return perms;
        }
    }

