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
import com.sju.program.domain.Menu;
import com.sju.program.service.IMenuService;

/**
 * 菜单Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/menu")
public class MenuController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    /**
     * 查询菜单列表
     */
    @PreAuthorize("@ss.hasPermi('program:menu:list')")
    @GetMapping("/list")
    public TableDataInfo list(Menu menu)
    {
        startPage();
        List<Menu> list = menuService.selectMenuList(menu);
        return getDataTable(list);
    }

//    /**
//     * 导出菜单列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:menu:export')")
//    @Log(title = "菜单", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Menu menu)
//    {
//        List<Menu> list = menuService.selectMenuList(menu);
//        ExcelUtil<Menu> util = new ExcelUtil<Menu>(Menu.class);
//        return util.exportExcel(list, "menu");
//    }

    /**
     * 获取菜单详细信息
     */
    @PreAuthorize("@ss.hasPermi('program:menu:query')")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable("menuId") Long menuId)
    {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    /**
     * 新增菜单
     */
    @PreAuthorize("@ss.hasPermi('program:menu:add')")
    @Log(title = "菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Menu menu)
    {
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@ss.hasPermi('program:menu:edit')")
    @Log(title = "菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Menu menu)
    {
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@ss.hasPermi('program:menu:remove')")
    @Log(title = "菜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{menuIds}")
    public AjaxResult remove(@PathVariable Long[] menuIds)
    {
        return toAjax(menuService.deleteMenuByIds(menuIds));
    }
}
