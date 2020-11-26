package com.sju.program.controller.login;

import com.sju.program.constant.Constants;
import com.sju.program.domain.Admin;
import com.sju.program.domain.Menu;
import com.sju.program.domain.Role;
import com.sju.program.domain.Admin;
import com.sju.program.domain.model.LoginBody;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.message.AjaxResult;
import com.sju.program.service.*;
import com.sju.program.service.login.LoginService;
import com.sju.program.service.IMenuService;
import com.sju.program.service.login.SysPermissionService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.JwtUtils;
import com.sju.program.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户登录验证
 *
 */
@Api(tags = "管理员登录接口(已停用)")
@RestController
public class AdminLoginController
{
    @Autowired
    private LoginService loginService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IAdminService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation(value = "验证管理员用户信息接口",notes = "验证用户信息,生成token")
    @PostMapping("/login/admin")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax=AjaxResult.success();
        Map<String,Object> playload=new HashMap<>();
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
        }
        catch (Exception e)
        {
            return AjaxResult.error("认证出错");
        }
        //LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //System.out.println(authentication);
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("*:*:*");
        LoginUser loginUser=(LoginUser) authentication.getPrincipal();
        try {
        Admin admin=(Admin) loginUser.getUser();
        if (admin==null||admin.getAdminDeleteFlag().equals("del")){
             return AjaxResult.error("用户不存在");
        }
        playload.put("userId",admin.getAdminId());
        playload.put("userName",admin.getAdminUsername());
        playload.put("delete_flag",admin.getAdminDeleteFlag());
        playload.put("permissions",arrayList);
        }catch (ClassCastException e){
            return AjaxResult.error("用户验证失败");
        }
        playload.put("authenticate",1);//管理员权限标识为1
        String token=JwtUtils.getToken(playload);

        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

//    @GetMapping("/login/info")
//    public User getLoginInfo(Authentication authentication){
//        return (User)authentication.getPrincipal();
//    }
//
//    /**
//     * 获取用户信息
//     *
//     * @return 用户信息
//     */
//    @ApiOperation(value = "获取管理员用户信息接口",notes = "获取权限和用户信息返回")
//    @GetMapping("/login/getAdminInfo")
//    public AjaxResult getInfo()
//    {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        Admin admin=(Admin) loginUser.getUser();
//        // 权限集合
//        ArrayList<String> permissions=new ArrayList<>();
//        permissions.add("*:*:*");
//        admin.setPermissions(permissions);
//        AjaxResult ajax = AjaxResult.success();
//        ajax.put("user", admin);
//        ajax.put("permissions",permissions);
//      //  ajax.put("roles", roles);
//        //ajax.put("departments", departments);
//        return ajax;
//    }

//    /**
//     * 获取菜单路由信息
//     *
//     * @return 菜单路由信息
//     */
//   // @PreAuthorize("@ss.hasPermi()")
//    @ApiOperation(value = "获取管理员路由信息接口",notes = "获取菜单信息返回")
//    @GetMapping("/login/getAdminRouters")
//    public AjaxResult getRouters()
//    {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        if(loginUser==null){
//            return AjaxResult.error("用户不存在");
//        }
//        List<Menu> menus = menuService.selectAllMenu();
//        List<Menu> menus1=menuService.getChildPerms(menus,0);
//        return AjaxResult.success(menuService.buildMenus(menus1));
//    }
}

