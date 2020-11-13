package com.sju.program.controller.login;

import com.sju.program.domain.*;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.message.AjaxResult;
import com.sju.program.service.IMenuService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class GetRouters {


    @Autowired
    private TokenService tokenService;

    @Autowired
    private IMenuService menuService;


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
}
