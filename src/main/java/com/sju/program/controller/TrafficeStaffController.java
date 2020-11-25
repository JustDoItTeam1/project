package com.sju.program.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.sju.program.domain.TrafficeStaff;
import com.sju.program.service.ITrafficeStaffService;

/**
 * 交管人员Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Api(tags={"交管人员管理"})
@RestController
@RequestMapping("/program/staff")
public class TrafficeStaffController extends BaseController
{
    @Autowired
    private ITrafficeStaffService trafficeStaffService;

    /**
     * 查询交管人员列表
     */
    @ApiOperation(value="查询交管人员列表")
    //@PreAuthorize("@ss.hasPermi('program:staff:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrafficeStaff trafficeStaff)
    {
        startPage();
        List<TrafficeStaff> list = trafficeStaffService.selectTrafficeStaffList(trafficeStaff);
        return getDataTable(list);
    }

//    /**
//     * 导出交管人员列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:staff:export')")
//    @Log(title = "交管人员", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(TrafficeStaffLoginController trafficeStaff)
//    {
//        List<TrafficeStaffLoginController> list = trafficeStaffService.selectTrafficeStaffList(trafficeStaff);
//        ExcelUtil<TrafficeStaffLoginController> util = new ExcelUtil<TrafficeStaffLoginController>(TrafficeStaffLoginController.class);
//        return util.exportExcel(list, "staff");
//    }

    /**
     * 获取交管人员详细信息
     */
    @ApiOperation(value="查询交管人员")
    //@PreAuthorize("@ss.hasPermi('program:staff:query')")
    @GetMapping(value = "/{trafficId}")
    public AjaxResult getInfo(@PathVariable("trafficId") Long trafficId)
    {
        return AjaxResult.success(trafficeStaffService.selectTrafficeStaffById(trafficId));
    }

    /**
     * 新增交管人员
     */
    @ApiOperation(value="新增交管人员")
    //@PreAuthorize("@ss.hasPermi('program:staff:add')")
    @Log(title = "交管人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrafficeStaff trafficeStaff)
    {
        return toAjax(trafficeStaffService.insertTrafficeStaff(trafficeStaff));
    }

    /**
     * 修改交管人员
     */
    @ApiOperation(value="修改交管人员")
    //@PreAuthorize("@ss.hasPermi('program:staff:edit')")
    @Log(title = "交管人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrafficeStaff trafficeStaff)
    {
        return toAjax(trafficeStaffService.updateTrafficeStaff(trafficeStaff));
    }

    /**
     * 删除交管人员
     */
    @ApiOperation(value="删除交管人员")
    //@PreAuthorize("@ss.hasPermi('program:staff:remove')")
    @Log(title = "交管人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{trafficIds}")
    public AjaxResult remove(@PathVariable Long[] trafficIds)
    {
        return toAjax(trafficeStaffService.deleteTrafficeStaffByIds(trafficIds));
    }
}
