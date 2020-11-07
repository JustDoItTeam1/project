package com.sju.program.controller;

import java.util.List;

import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
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
    @PreAuthorize("@ss.hasPermi('program:scheme:list')")
    @GetMapping("/list")
    public TableDataInfo list(SiegeScheme siegeScheme)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SiegeScheme> list=tokenService.getLoginUserSiegeScheme(loginUser);
        return getDataTable(list);
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
    @PreAuthorize("@ss.hasPermi('program:scheme:query')")
    @GetMapping(value = "/{ssId}")
    public AjaxResult getInfo(@PathVariable("ssId") Long ssId)
    {
        return AjaxResult.success(siegeSchemeService.selectSiegeSchemeById(ssId));
    }

    /**
     * 新增围蔽方案
     */
    @PreAuthorize("@ss.hasPermi('program:scheme:add')")
    @Log(title = "围蔽方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SiegeScheme siegeScheme)
    {
        return toAjax(siegeSchemeService.insertSiegeScheme(siegeScheme));
    }

    /**
     * 修改围蔽方案
     */
    @PreAuthorize("@ss.hasPermi('program:scheme:edit')")
    @Log(title = "围蔽方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SiegeScheme siegeScheme)
    {
        return toAjax(siegeSchemeService.updateSiegeScheme(siegeScheme));
    }

    /**
     * 删除围蔽方案
     */
    @PreAuthorize("@ss.hasPermi('program:scheme:remove')")
    @Log(title = "围蔽方案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ssIds}")
    public AjaxResult remove(@PathVariable Long[] ssIds)
    {
        return toAjax(siegeSchemeService.deleteSiegeSchemeByIds(ssIds));
    }
}
