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
import com.sju.program.domain.Admin;
import com.sju.program.service.IAdminService;
import com.sju.program.page.TableDataInfo;

/**
 * 管理员账户Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/admin")
public class AdminController extends BaseController
{
    @Autowired
    private IAdminService adminService;

    /**
     * 查询管理员账户列表
     */
    //@PreAuthorize("@ss.hasPermi('program:admin:list')")
    @GetMapping("/list")
    public TableDataInfo list(Admin admin)
    {
        startPage();
        List<Admin> list = adminService.selectAdminList(admin);
        return getDataTable(list);
    }

//    /**
//     * 导出管理员账户列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:admin:export')")
//    @Log(title = "管理员账户", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Admin admin)
//    {
//        List<Admin> list = adminService.selectAdminList(admin);
//        ExcelUtil<Admin> util = new ExcelUtil<Admin>(Admin.class);
//        return util.exportExcel(list, "admin");
//    }

    /**
     * 获取管理员账户详细信息
     */
    //@PreAuthorize("@ss.hasPermi('program:admin:query')")
    @GetMapping(value = "/{adminId}")
    public AjaxResult getInfo(@PathVariable("adminId") Long adminId)
    {
        return AjaxResult.success(adminService.selectAdminById(adminId));
    }

    /**
     * 新增管理员账户
     */
    //@PreAuthorize("@ss.hasPermi('program:admin:add')")
    @Log(title = "管理员账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Admin admin)
    {
        return toAjax(adminService.insertAdmin(admin));
    }

    /**
     * 修改管理员账户
     */
    //@PreAuthorize("@ss.hasPermi('program:admin:edit')")
    @Log(title = "管理员账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Admin admin)
    {
        return toAjax(adminService.updateAdmin(admin));
    }

    /**
     * 删除管理员账户
     */
    //@PreAuthorize("@ss.hasPermi('program:admin:remove')")
    @Log(title = "管理员账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{adminIds}")
    public AjaxResult remove(@PathVariable Long[] adminIds)
    {
        return toAjax(adminService.deleteAdminByIds(adminIds));
    }
}
