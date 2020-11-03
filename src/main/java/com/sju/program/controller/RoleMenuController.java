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
import com.sju.program.domain.RoleMenu;
import com.sju.program.service.IRoleMenuService;

/**
 * 权限菜单Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/rolemenu")
public class RoleMenuController extends BaseController
{
    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 查询权限菜单列表
     */
    @PreAuthorize("@ss.hasPermi('program:menu:list')")
    @GetMapping("/list")
    public TableDataInfo list(RoleMenu roleMenu)
    {
        startPage();
        List<RoleMenu> list = roleMenuService.selectRoleMenuList(roleMenu);
        return getDataTable(list);
    }

//    /**
//     * 导出权限菜单列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:menu:export')")
//    @Log(title = "权限菜单", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(RoleMenu roleMenu)
//    {
//        List<RoleMenu> list = roleMenuService.selectRoleMenuList(roleMenu);
//        ExcelUtil<RoleMenu> util = new ExcelUtil<RoleMenu>(RoleMenu.class);
//        return util.exportExcel(list, "menu");
//    }

    /**
     * 获取权限菜单详细信息
     */
    @PreAuthorize("@ss.hasPermi('program:menu:query')")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return AjaxResult.success(roleMenuService.selectRoleMenuById(roleId));
    }

    /**
     * 新增权限菜单
     */
    @PreAuthorize("@ss.hasPermi('program:menu:add')")
    @Log(title = "权限菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RoleMenu roleMenu)
    {
        return toAjax(roleMenuService.insertRoleMenu(roleMenu));
    }

    /**
     * 修改权限菜单
     */
    @PreAuthorize("@ss.hasPermi('program:menu:edit')")
    @Log(title = "权限菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RoleMenu roleMenu)
    {
        return toAjax(roleMenuService.updateRoleMenu(roleMenu));
    }

    /**
     * 删除权限菜单
     */
    @PreAuthorize("@ss.hasPermi('program:menu:remove')")
    @Log(title = "权限菜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(roleMenuService.deleteRoleMenuByIds(roleIds));
    }
}
