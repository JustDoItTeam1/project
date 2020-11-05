package com.sju.program.controller.login;

import com.sju.program.constant.Constants;
import com.sju.program.domain.*;
import com.sju.program.domain.Admin;
import com.sju.program.domain.model.LoginBody;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.message.AjaxResult;
import com.sju.program.security.token.PoliceUsernamePasswordAuthenticationToken;
import com.sju.program.security.token.TrafficStaffUsernamePasswordAuthticationToekn;
import com.sju.program.service.*;
import com.sju.program.service.login.LoginService;
import com.sju.program.service.IMenuService;
import com.sju.program.service.login.SysPermissionService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.JwtUtils;
import com.sju.program.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
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
import java.util.Set;

/**
 * 用户1登录验证
 *
 */
@Api(tags = "登录接口")
@RestController
public class TrafficeStafficLoginController
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
    @ApiOperation(value = "验证用户信息接口",notes = "验证用户信息,生成token")
    @PostMapping("/login/traffic")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        Map<String, Object> playload = new HashMap<>();
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new TrafficStaffUsernamePasswordAuthticationToekn(loginBody.getUsername(), loginBody.getPassword()));
        } catch (Exception e) {
            return AjaxResult.error("用户验证失败");
        }
        //LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //System.out.println(authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        try {
            TrafficeStaff trafficeStaff = (TrafficeStaff) loginUser.getUser();
            if (trafficeStaff == null || trafficeStaff.getTrafficDeleteFlag().equals("del")) {
                return AjaxResult.error("用户不存在");
            }
            playload.put("userId", trafficeStaff.getTrafficId());
            playload.put("userName", trafficeStaff.getTrafficName());
            playload.put("delete_flag", trafficeStaff.getTrafficDeleteFlag());
        }catch (ClassCastException e){
            return AjaxResult.error("用户验证失败");
        }
        playload.put("authenticate",3);
        String token=JwtUtils.getToken(playload);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/login/getTrafficInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        TrafficeStaff trafficeStaff=(TrafficeStaff) loginUser.getUser();
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(trafficeStaff.getAuthenticate());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", trafficeStaff);
        ajax.put("permissions",permissions);
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
    @GetMapping("/login/getTrafficRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(loginUser==null){
            return AjaxResult.error("用户不存在");
        }
        List<Menu> menus = menuService.selectMenusByAuthenticate(((TrafficeStaff)loginUser.getUser()).getAuthenticate());
        return AjaxResult.success(menus);
    }
}



