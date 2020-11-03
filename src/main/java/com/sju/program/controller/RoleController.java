package com.sju.program.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sju.program.annotation.Log;
import com.sju.program.message.AjaxResult;
import com.sju.program.enums.BusinessType;
import com.sju.program.page.TableDataInfo;
import com.sju.program.domain.Role;
import com.sju.program.service.IRoleService;

/**
 * 权限Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/role")
public class RoleController extends BaseController
{
    @Autowired
    private IRoleService roleService;

    /**
     * 查询权限列表
     */
    @PreAuthorize("@ss.hasPermi('program:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(Role role)
    {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

//    /**
//     * 导出权限列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:role:export')")
//    @Log(title = "权限", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Role role)
//    {
//        List<Role> list = roleService.selectRoleList(role);
//        ExcelUtil<Role> util = new ExcelUtil<Role>(Role.class);
//        return util.exportExcel(list, "role");
//    }

    /**
     * 获取权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('program:role:query')")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增权限
     */
    @PreAuthorize("@ss.hasPermi('program:role:add')")
    @Log(title = "权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Role role)
    {
        return toAjax(roleService.insertRole(role));
    }

    /**
     * 修改权限
     */
    @PreAuthorize("@ss.hasPermi('program:role:edit')")
    @Log(title = "权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Role role)
    {
        return toAjax(roleService.updateRole(role));
    }

    /**
     * 删除权限
     */
    @PreAuthorize("@ss.hasPermi('program:role:remove')")
    @Log(title = "权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }
}
