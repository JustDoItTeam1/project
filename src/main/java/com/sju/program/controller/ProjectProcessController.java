package com.sju.program.controller;

import java.util.List;

import com.sju.program.domain.SiegeScheme;
import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.domain.vo.NoticeToBuilder;
import com.sju.program.domain.vo.ProjectProcessVo;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
import com.sun.org.glassfish.gmbal.ParameterNames;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Api(tags = "施工进度管理接口")
@RestController
@RequestMapping("/program/process")
public class ProjectProcessController extends BaseController
{
    @Autowired
    private IProjectProcessService projectProcessService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询施工进度列表
     */
    @ApiOperation("获取进度列表")
    @PreAuthorize("@ss.hasPermi('project:process:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectProcess projectProcess)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<ProjectProcess> list=tokenService.getLoginUserProjectProcess(loginUser);
        List<ProjectProcessVo> projectProcessVoList=projectProcessService.projectProcessVoList(list);
        return getDataTable(projectProcessVoList);
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
    @ApiOperation("获取id为x的进度")
    @PreAuthorize("@ss.hasPermi('project:process:query')")
    @GetMapping(value = "/{ppId}")
    public AjaxResult getInfo(@PathVariable("ppId") Long ppId)
    {
        return AjaxResult.success(projectProcessService.selectProjectProcessById(ppId));
    }

    /**
     * 新增施工进度
     */
    @ApiOperation("新增施工进度")
    @PreAuthorize("@ss.hasPermi('project:process:add')")
    @Log(title = "施工进度", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectProcess projectProcess)
    {
        return toAjax(projectProcessService.insertProjectProcess(projectProcess));
    }

    /**
     * 修改施工进度
     */
    @ApiOperation("修改施工进度")
    @PreAuthorize("@ss.hasPermi('project:process:edit')")
    @Log(title = "施工进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectProcess projectProcess)
    {
        return toAjax(projectProcessService.updateProjectProcess(projectProcess));
    }

    /**
     * 删除施工进度
     */
    @ApiOperation("删除施工进度(可批量删除)")
    @PreAuthorize("@ss.hasPermi('project:process:remove')")
    @Log(title = "施工进度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ppIds}")
    public AjaxResult remove(@PathVariable Long[] ppIds)
    {
        return toAjax(projectProcessService.deleteProjectProcessByIds(ppIds));
    }

    @GetMapping("")
    public TableDataInfo search(@RequestParam("projectName") String projectName){
        startPage();
        LoginUser loginUser=tokenService.getLoginUser(ServletUtils.getRequest());
        BaseUser baseUser=(BaseUser)loginUser.getUser();
        return getDataTable(projectProcessService.selectProjectProcessByProjectName(baseUser,projectName));
    }
}
