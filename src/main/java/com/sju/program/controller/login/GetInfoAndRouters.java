package com.sju.program.controller.login;

import com.sju.program.domain.*;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.message.AjaxResult;
import com.sju.program.service.IMenuService;
import com.sju.program.service.IProjectService;
import com.sju.program.service.login.PermissionService;
import com.sju.program.service.login.SysPermissionService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Api(tags = "获取用户信息和路由信息")
@RestController
public class GetInfoAndRouters {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private IProjectService projectService;


    @ApiOperation(value = "获取路由接口",notes = "获取菜单返回")
    @GetMapping("/getRouters")
    public AjaxResult getRouters()
    {
        List<Menu> menus=null;
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(loginUser==null){
            return AjaxResult.error("用户不存在");
        }
        if(loginUser.getUser().getClass()== Admin.class){
            menus = menuService.selectAllMenu();
        }
        if(loginUser.getUser().getClass()==Police.class){
            menus = menuService.selectMenusByAuthenticate(((Police)loginUser.getUser()).getAuthenticate());
        }
        if(loginUser.getUser().getClass()==TrafficeStaff.class){
            menus = menuService.selectMenusByAuthenticate(((TrafficeStaff)loginUser.getUser()).getAuthenticate());
        }
        if(loginUser.getUser().getClass()==Builder.class){
            menus = menuService.selectMenusByAuthenticate(((Builder)loginUser.getUser()).getAuthenticate());
        }
        List<Menu> menus1=menuService.getChildPerms(menus,0);
        return AjaxResult.success(menuService.buildMenus(menus1));
    }



    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation(value = "获取用户信息接口",notes = "获取权限和用户信息返回")
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        ArrayList<String> permissions=new ArrayList<>();
        AjaxResult ajax = AjaxResult.success();
        if (loginUser.getUser().getClass()==Admin.class){
            Admin admin=(Admin) loginUser.getUser();
            // 权限集合
            permissions.add("*:*:*");
            admin.setPermissions(permissions);
            ajax.put("user", admin);
            ajax.put("permissions",permissions);
            ajax.put("roles","admin");
        }

        if (loginUser.getUser().getClass()==Builder.class){
            Builder builder=(Builder) loginUser.getUser();
            // 权限集合
            permissions = permissionService.getMenuPermission(builder.getAuthenticate());
            List<Project> list=projectService.selectProjectListBySubmiteprocessFlag();
            builder.setPermissions(permissions);
            ajax.put("user", builder);
            ajax.put("permissions",permissions);
            ajax.put("roles","common");
            ajax.put("OverdueProjectInformation",list);
        }
        if (loginUser.getUser().getClass()==TrafficeStaff.class){
            TrafficeStaff trafficeStaff=(TrafficeStaff) loginUser.getUser();
            // 权限集合
            permissions = permissionService.getMenuPermission(trafficeStaff.getAuthenticate());
            trafficeStaff.setPermissions(permissions);
            ajax.put("user", trafficeStaff);
            ajax.put("permissions",permissions);
            ajax.put("roles","common");
        }
        if (loginUser.getUser().getClass()==Police.class){
            Police police=(Police) loginUser.getUser();
            // 权限集合
            permissions = permissionService.getMenuPermission(police.getAuthenticate());
            police.setPermissions(permissions);
            ajax.put("user", police);
            ajax.put("permissions",permissions);
            ajax.put("roles","common");
        }
        return ajax;
    }

}
