package com.sju.program.controller;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sju.program.constant.HttpStatus;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.domain.vo.ProjectVo;
import com.sju.program.domain.vo.SignInfoVo;
import com.sju.program.service.SignService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
import com.sju.program.utils.StringUtils;
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
import com.sju.program.domain.Project;
import com.sju.program.service.IProjectService;

/**
 * 施工项目Controller
 *
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Api(tags = "施工项目接口")
@RestController
@RequestMapping("/program/project")
public class ProjectController extends BaseController
{
    @Autowired
    private IProjectService projectService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SignService signService;

    /**
     * 查询施工项目列表
     */
    @ApiOperation("获取施工项目列表")
    @PreAuthorize("@ss.hasPermi('project:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<Project> list = tokenService.getLoginUserProject(loginUser);
        List<SignInfoVo> signInfoVos = tokenService.getLoginUserSignInfo(loginUser);
        if (StringUtils.isNotEmpty(project.getProjectInfo())){
            List<Project> projectList=projectService.selectSiegeSchemeBySearch(list,project.getProjectInfo());
            List<SignInfoVo> signInfoList=signService.selectSignInfoByName(signInfoVos,project.getProjectInfo());
            if (projectList.size()>0){
                TableDataInfo rspData = new TableDataInfo();
                rspData.setCode(HttpStatus.SUCCESS);
                rspData.setMsg("项目查询成功");
                rspData.setRows(projectList);
                rspData.setTotal(new PageInfo(projectList).getTotal());
                return rspData;
            }else{
                TableDataInfo rspData = new TableDataInfo();
                rspData.setCode(HttpStatus.SUCCESS);
                rspData.setMsg("标牌查询成功");
                rspData.setRows(signInfoList);
                rspData.setTotal(new PageInfo(signInfoList).getTotal());
                return rspData;
            }
        }
        return getDataTable(new ArrayList<>());
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
    @ApiOperation("获取id为x的施工项目信息")
    @PreAuthorize("@ss.hasPermi('project:project:edit')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return AjaxResult.success(projectService.selectProjectById(projectId));
    }

    /**
     * 新增施工项目
     */
    @ApiOperation("新增施工项目")
    @PreAuthorize("@ss.hasPermi('project:project:add')")
    @Log(title = "施工项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Project project)
    {
        String name=project.getProjectName();
        if (StringUtils.isNotEmpty(projectService.unique(name))){
            return AjaxResult.error("与已有项目名称重复，增加失败");
        }
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改施工项目
     */
    @ApiOperation("修改施工项目")
    @PreAuthorize("@ss.hasPermi('project:project:edit')")
    @Log(title = "施工项目", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody Project project)
    {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除施工项目
     */
    @ApiOperation("删除施工项目(可批量删除)")
    @PreAuthorize("@ss.hasPermi('project:project:remove')")
    @Log(title = "施工项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectService.deleteProjectByIds(projectIds));
    }
}
