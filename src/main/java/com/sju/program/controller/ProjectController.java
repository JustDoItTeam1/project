package com.sju.program.controller;

import java.util.List;

import com.sju.program.domain.SiegeScheme;
import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
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
import com.sju.program.domain.Project;
import com.sju.program.service.IProjectService;
import com.sju.program.domain.Builder;

/**
 * 施工项目Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/project")
public class ProjectController extends BaseController {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询施工项目列表
     */
    //@PreAuthorize("@ss.hasPermi('program:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project) {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<Project> list = tokenService.getLoginUserProject(loginUser);
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

    /**
     * 获取施工项目详细信息
     */
    @ApiOperation(value = "获取施工项目详细信息")
    @PreAuthorize("@ss.hasPermi('program:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId) {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<Project> list = null;
        BaseUser baseUser = (BaseUser) loginUser.getUser();
        switch (baseUser.getAuthenticate()) {
            case 1:
            case 3:
                list = projectService.selectProject(projectId);
                break;
            case 4:
                list = projectService.selectProjectById(projectId, baseUser.getId());
                break;

        }
        return AjaxResult.success(list);
    }

    /**
     * 新增施工项目
     */
    @ApiOperation(value = "新增施工项目")
    //@PreAuthorize("@ss.hasPermi('program:project:add')")
    @Log(title = "施工项目", businessType = BusinessType.INSERT)
    @PostMapping("/addProject")
    public AjaxResult add(@RequestBody Project project) {
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 搜索施工项目
     */
    //@ApiOperation(value ="搜索施工项目")
    //@PreAuthorize("@ss.hasPermi('program:project:search')")
    //@Log(title = "施工项目", businessType = BusinessType.INSERT)
    //@PostMapping("/searchProject")
    // AjaxResult search(@RequestBody Project project){
    //return AjaxResult.success(projectService.selectProject(project));
    //}

    /**
     * 修改施工项目
     */
    @ApiOperation(value = "修改施工单位")
    @PreAuthorize("@ss.hasPermi('program:project:edit')")
    @Log(title = "施工项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Project project) {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除施工项目
     */
    @ApiOperation(value = "删除施工单位")
    @PreAuthorize("@ss.hasPermi('program:project:remove')")
    @Log(title = "施工项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds) {
        return toAjax(projectService.deleteProjectByIds(projectIds));
    }

    @ApiOperation(value = "按项目名模糊搜索")
    //@PreAuthorize("@ss.hasPermi('program:project:search1')")
    @Log(title = "施工项目", businessType = BusinessType.INSERT)
    @GetMapping(value = "/{projectName}")
    public AjaxResult search1(@PathVariable("projectName") String projectName) {
        if (projectName == null) {
            return AjaxResult.error("搜索内容请勿为空");
        }
        return AjaxResult.success(projectService.selectProjectByName(projectName));
    }

    @ApiOperation(value = "按施工单位模糊搜索")
    //@PreAuthorize("@ss.hasPermi('program:project:search2')")
    @Log(title = "施工项目", businessType = BusinessType.INSERT)
    @GetMapping(value = "/{projectBuilderName}")
    public AjaxResult search2(@PathVariable("projectBuilderName") String projectBuilderName) {
        if (projectBuilderName == null) {
            return AjaxResult.error("搜索内容请勿为空");
        }
        return AjaxResult.success(projectService.selectProjectByBuilder(projectBuilderName));
    }

    @ApiOperation(value = "按负责人模糊搜索")
    //@PreAuthorize("@ss.hasPermi('program:project:search3')")
    @Log(title = "施工项目", businessType = BusinessType.INSERT)
    @GetMapping(value = "/{projectManger}")
    public AjaxResult search3(@PathVariable("projectManger") String projectManger) {
        if (projectManger == null) {
            return AjaxResult.error("搜索内容请勿为空");
        }
        return AjaxResult.success(projectService.selectProjectByManger(projectManger));
    }
}
