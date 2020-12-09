package com.sju.program.controller;

import java.util.List;
import java.util.Map;

import com.sju.program.constant.UserConstants;
import com.sju.program.domain.Admin;
import com.sju.program.domain.Project;
import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.domain.vo.SieheSchemeParentVo;
import com.sju.program.message.HttpStatus;
import com.sju.program.message.Test;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
import com.sju.program.domain.SiegeScheme;
import com.sju.program.service.ISiegeSchemeService;

/**
 * 围蔽方案Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Api(tags = "围蔽方案管理接口")
@RestController
@RequestMapping("/program/scheme")
public class SiegeSchemeController extends BaseController
{
    @Autowired
    private ISiegeSchemeService siegeSchemeService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询围蔽方案列表
     */
    @ApiOperation(value = "查询围蔽方案接口",notes = "根据不同用户信息,查询其围蔽方案")
    @PreAuthorize("@ss.hasPermi('enclosure:scheme:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SiegeScheme> list=tokenService.getLoginUserSiegeScheme(loginUser);
        List<SieheSchemeParentVo> sieheSchemeParentVoList=siegeSchemeService.buildSiegeScheme(list);
        if (project.getProjectInfo()==null){
            return getDataTable(sieheSchemeParentVoList);
        }
        else {
            List<SieheSchemeParentVo> sieheSchemeParentVoList1=siegeSchemeService.selectSiegeSchemeBySearch(sieheSchemeParentVoList,project.getProjectInfo());
            return getDataTable(sieheSchemeParentVoList1);
        }
       // return getDataTable(sieheSchemeParentVoList);
    }

//    /**
//     * 导出围蔽方案列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:scheme:export')")
//    @Log(title = "围蔽方案", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(SiegeScheme siegeScheme)
//    {
//        List<SiegeScheme> list = siegeSchemeService.selectSiegeSchemeList(siegeScheme);
//        ExcelUtil<SiegeScheme> util = new ExcelUtil<SiegeScheme>(SiegeScheme.class);
//        return util.exportExcel(list, "scheme");
//    }

    /**
     * 获取围蔽方案详细信息
     */
    @ApiOperation(value = "查询单个围蔽方案接口")
    @PreAuthorize("@ss.hasPermi('enclosure:scheme:query')")
    @GetMapping(value = "/{ssId}")
    public AjaxResult getInfo(@PathVariable("ssId") Long ssId)
    {
        return AjaxResult.success(siegeSchemeService.selectSiegeSchemeById(ssId));
    }

    /**
     * 根据项目id获取围蔽方案详细信息
     */
    @ApiOperation(value = "根据项目id获取围蔽方案详细信息")
    @PreAuthorize("@ss.hasPermi('enclosure:scheme:query')")
    @GetMapping(value = "/byProjectId/{projectId}")
    public TableDataInfo getInfoByprojectId(@PathVariable("projectId") Long projectId)
    {
        List<SiegeScheme> list=siegeSchemeService.selectSiegeSchemeByprojectIdIncludeProjectId(projectId);
        List<SieheSchemeParentVo> sieheSchemeParentVoList=siegeSchemeService.buildSiegeScheme(list);
        return  getDataTable(sieheSchemeParentVoList);
    }

    /**
     * 新增围蔽方案
     */
    @ApiOperation(value = "新增围蔽方案接口",notes = "围蔽阶段或围蔽状态信息已存在则新增失败")
    @PreAuthorize("@ss.hasPermi('enclosure:scheme:add')")
    @Log(title = "围蔽方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SiegeScheme siegeScheme)
    {
        if (UserConstants.NOT_UNIQUE.equals(siegeSchemeService.checkSifegeSchemeUnique(siegeScheme))){
            return AjaxResult.error("新增围蔽失败，围蔽阶段或围蔽状态信息已存在");
        }
        return toAjax(siegeSchemeService.insertSiegeScheme(siegeScheme));
    }

    /**
     * 修改围蔽方案
     */
    @ApiOperation(value = "修改围蔽方案接口")
    @PreAuthorize("@ss.hasPermi('enclosure:scheme:edit')")
    @Log(title = "围蔽方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SiegeScheme siegeScheme)
    {
        return toAjax(siegeSchemeService.updateSiegeScheme(siegeScheme));
    }

    /**
     * 删除围蔽方案
     */
    @ApiOperation(value = "伪删除围蔽方案接口",notes = "支持批量删除 格式：/program/scheme/1,2")
    @PreAuthorize("@ss.hasPermi('enclosure:scheme:remove')")
    @Log(title = "围蔽方案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ssIds}")
    public AjaxResult remove(@PathVariable Long[] ssIds)
    {
        return toAjax(siegeSchemeService.deleteSiegeSchemeByIds(ssIds));
    }
}
