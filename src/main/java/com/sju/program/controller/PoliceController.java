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
import com.sju.program.domain.Police;
import com.sju.program.service.IPoliceService;


/**
 * 交警人员Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Api(tags={"交警人员管理"})
@RestController
@RequestMapping("/program/police")
public class PoliceController extends BaseController
{
    @Autowired
    private IPoliceService policeService;

    /**
     * 查询交警人员列表
     */
    @ApiOperation(value="查询交警人员列表")
    //@PreAuthorize("@ss.hasPermi('program:police:list')")
    @GetMapping("/list")
    public TableDataInfo list(Police police)
    {
        startPage();
        List<Police> list = policeService.selectPoliceList(police);
        return getDataTable(list);
    }

//    /**
//     * 导出交警人员列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:police:export')")
//    @Log(title = "交警人员", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Police police)
//    {
//        List<Police> list = policeService.selectPoliceList(police);
//        ExcelUtil<Police> util = new ExcelUtil<Police>(Police.class);
//        return util.exportExcel(list, "police");
//    }

    /**
     * 获取交警人员详细信息
     */
    @ApiOperation(value="查询交警人员")
    //@PreAuthorize("@ss.hasPermi('program:police:query')")
    @GetMapping(value = "/{policeId}")
    public AjaxResult getInfo(@PathVariable("policeId") Long policeId)
    {
        return AjaxResult.success(policeService.selectPoliceById(policeId));
    }

    /**
     * 新增交警人员
     */
    @ApiOperation(value="新增交警人员")
    //@PreAuthorize("@ss.hasPermi('program:police:add')")
    @Log(title = "交警人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Police police)
    {
        return toAjax(policeService.insertPolice(police));
    }

    /**
     * 修改交警人员
     */
    @ApiOperation(value="修改交警人员")
    //@PreAuthorize("@ss.hasPermi('program:police:edit')")
    @Log(title = "交警人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Police police)
    {
        return toAjax(policeService.updatePolice(police));
    }

    /**
     * 删除交警人员
     */
    @ApiOperation(value="删除交警人员")
    //@PreAuthorize("@ss.hasPermi('program:police:remove')")
    @Log(title = "交警人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{policeIds}")
    public AjaxResult remove(@PathVariable Long[] policeIds)
    {
        return toAjax(policeService.deletePoliceByIds(policeIds));
    }
}
