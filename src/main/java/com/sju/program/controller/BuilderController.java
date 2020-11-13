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
import com.sju.program.domain.Builder;
import com.sju.program.service.IBuilderService;

/**
 * 施工单位Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/builder")
public class
BuilderController extends BaseController
{
    @Autowired
    private IBuilderService builderService;

    /**
     * 查询施工单位列表
     */
    //@PreAuthorize("@ss.hasPermi('program:builder:list')")
    @GetMapping("/list")
    public TableDataInfo list(Builder builder)
    {
        startPage();
        List<Builder> list = builderService.selectBuilderList(builder);
        return getDataTable(list);
    }


//    /**
//     * 导出施工单位列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:builder:export')")
//    @Log(title = "施工单位", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Builder builder)
//    {
//        List<Builder> list = builderService.selectBuilderList(builder);
//        ExcelUtil<Builder> util = new ExcelUtil<Builder>(Builder.class);
//        return util.exportExcel(list, "builder");
//    }

    /**
     * 获取施工单位详细信息
     */
    //@PreAuthorize("@ss.hasPermi('program:builder:query')")
    @GetMapping(value = "/{builderId}")
    public AjaxResult getInfo(@PathVariable("builderId") Long builderId)
    {
        return AjaxResult.success(builderService.selectBuilderById(builderId));
    }

    /**
     * 新增施工单位
     */
    //@PreAuthorize("@ss.hasPermi('program:builder:add')")
    @Log(title = "施工单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Builder builder)
    {
        return toAjax(builderService.insertBuilder(builder));
    }

    /**
     * 修改施工单位
     */
    //@PreAuthorize("@ss.hasPermi('program:builder:edit')")
    @Log(title = "施工单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Builder builder)
    {
        return toAjax(builderService.updateBuilder(builder));
    }

    /**
     * 删除施工单位
     */
    //@PreAuthorize("@ss.hasPermi('program:builder:remove')")
    @Log(title = "施工单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{builderIds}")
    public AjaxResult remove(@PathVariable Long[] builderIds)
    {
        return toAjax(builderService.deleteBuilderByIds(builderIds));
    }
}
