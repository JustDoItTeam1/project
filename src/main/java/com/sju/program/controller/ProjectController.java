package com.sju.program.controller;

import java.util.List;

import com.sju.program.domain.model.LoginUser;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
import com.sju.program.utils.StringUtils;
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
import com.sju.program.domain.Project;
import com.sju.program.service.IProjectService;

/**
 * 施工项目Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/project")
public class ProjectController extends BaseController
{
    @Autowired
    private IProjectService projectService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询施工项目列表
     */
    //@PreAuthorize("@ss.hasPermi('program:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<Project> list = tokenService.getLoginUserProject(loginUser);
        if (StringUtils.isNotEmpty(project.getProjectInfo())){
            return getDataTable(projectService.selectSiegeSchemeBySearch(list,project.getProjectInfo()));
        }
        return getDataTable(list);
    }

    /**
     * 导出施工项目列表
     */
//    @PreAuthorize("@ss.hasPermi('program:project:export')")
//    @Log(title = "施工项目", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(Project project)
//    {
//        List<Project> list = projectService.selectProjectList(project);
//        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
//        return util.exportExcel(list, "project");
//    }

    //@PreAuthorize("@ss.hasPermi('program:project:query')")
    /**
     * 获取施工项目详细信息
     */
    //@PreAuthorize("@ss.hasPermi('program:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return AjaxResult.success(projectService.selectProjectById(projectId));
    }

    /**
     * 新增施工项目
     */
    @PreAuthorize("@ss.hasPermi('program:project:add')")
    @Log(title = "施工项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Project project)
    {
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改施工项目
     */
    @PreAuthorize("@ss.hasPermi('program:project:edit')")
    @Log(title = "施工项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Project project)
    {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除施工项目
     */
    @PreAuthorize("@ss.hasPermi('program:project:remove')")
    @Log(title = "施工项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectService.deleteProjectByIds(projectIds));
    }
}
