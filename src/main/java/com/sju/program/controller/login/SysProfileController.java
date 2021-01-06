package com.sju.program.controller.login;


import com.sju.program.annotation.Log;
import com.sju.program.domain.Admin;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.enums.BusinessType;
import com.sju.program.message.AjaxResult;
import com.sju.program.service.IAdminService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.SecurityUtils;
import com.sju.program.utils.ServletUtils;
import com.sju.program.utils.file.FileUploadUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 个人信息 业务处理
 * 
 * @author ruoyi
 */
@Api(tags = "管理员修改密码")
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController
{
    @Autowired
    private IAdminService adminService;

    @Autowired
    private TokenService tokenService;


    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName=loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (adminService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }
}
