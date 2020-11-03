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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户1登录验证
 *
 */
@Api(tags = "登录接口")
@RestController
public class AdminLoginController
{
    @Autowired
    private LoginService loginService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

//    @Autowired
//    private SysPermissionService permissionService;

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
    @ApiOperation(value = "验证用户信息接口",notes = "验证用户信息,生成token")
    @PostMapping("/login/test")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax=new AjaxResult();
        Map<String,Object> playload=new HashMap<>();
        // 查詢是否用戶存在
        //User  user = userService.login(loginBody.getUsername(), loginBody.getPassword());
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        System.out.println(authentication);
        LoginUser loginUser=(LoginUser) authentication.getPrincipal();
        Admin admin=(Admin) loginUser.getUser();
        if (admin==null){
             return AjaxResult.error();
        }
        playload.put("userId",admin.getAdminId());
        playload.put("userName",admin.getAdminUsername());
//        user.authentication
        String token=JwtUtils.getToken(playload);
        ajax.put("staus",true);
        ajax.put("msg","认证成功");
        //ajax.put("user",user);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

//    @GetMapping("/login/info")
//    public User getLoginInfo(Authentication authentication){
//        return (User)authentication.getPrincipal();
//    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/login/getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Admin admin=(Admin) loginUser.getUser();
        //User user = loginUser.getUser();
        // 角色集合
        //List<Role> roles = roleService.selectRoleList();
        // 部门集合
       // List<Department> departments = departmentService.selectDepartmentByUserId(user.getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", admin);
      //  ajax.put("roles", roles);
        //ajax.put("departments", departments);
        return ajax;
    }

    /**
     * 获取菜单路由信息
     *
     * @return 菜单路由信息
     */
   // @PreAuthorize("@ss.hasPermi()")
    @GetMapping("/login/getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
       // User user = userService.getUser();
        //List<Menu> menus = menuService.selectMenuList();
        List<Menu> menus=null ;
        //System.out.println(menus);
        return AjaxResult.success(menus);
    }
}

