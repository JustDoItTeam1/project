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
import com.sju.program.domain.ProjectProcess;
import com.sju.program.service.IProjectProcessService;

/**
 * 施工进度Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/process")
public class ProjectProcessController extends BaseController
{
    @Autowired
    private IProjectProcessService projectProcessService;

    /**
     * 查询施工进度列表
     */
    @PreAuthorize("@ss.hasPermi('program:process:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectProcess projectProcess)
    {
        startPage();
        List<ProjectProcess> list = projectProcessService.selectProjectProcessList(projectProcess);
        return getDataTable(list);
    }

//    /**
//     * 导出施工进度列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:process:export')")
//    @Log(title = "施工进度", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(ProjectProcess projectProcess)
//    {
//        List<ProjectProcess> list = projectProcessService.selectProjectProcessList(projectProcess);
//        ExcelUtil<ProjectProcess> util = new ExcelUtil<ProjectProcess>(ProjectProcess.class);
//        return util.exportExcel(list, "process");
//    }

    /**
     * 获取施工进度详细信息
     */
    @PreAuthorize("@ss.hasPermi('program:process:query')")
    @GetMapping(value = "/{ppId}")
    public AjaxResult getInfo(@PathVariable("ppId") Long ppId)
    {
        return AjaxResult.success(projectProcessService.selectProjectProcessById(ppId));
    }

    /**
     * 新增施工进度
     */
    @PreAuthorize("@ss.hasPermi('program:process:add')")
    @Log(title = "施工进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectProcess projectProcess)
    {
        return toAjax(projectProcessService.insertProjectProcess(projectProcess));
    }

    /**
     * 修改施工进度
     */
    @PreAuthorize("@ss.hasPermi('program:process:edit')")
    @Log(title = "施工进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectProcess projectProcess)
    {
        return toAjax(projectProcessService.updateProjectProcess(projectProcess));
    }

    /**
     * 删除施工进度
     */
    @PreAuthorize("@ss.hasPermi('program:process:remove')")
    @Log(title = "施工进度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ppIds}")
    public AjaxResult remove(@PathVariable Long[] ppIds)
    {
        return toAjax(projectProcessService.deleteProjectProcessByIds(ppIds));
    }
}
