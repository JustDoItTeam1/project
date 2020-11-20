package com.sju.program.controller;

import java.util.List;

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
import com.sju.program.domain.RectificationInfo;
import com.sju.program.service.IRectificationInfoService;


/**
 * 整改信息Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/program/info")
public class RectificationInfoController extends BaseController
{
    @Autowired
    private IRectificationInfoService rectificationInfoService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询整改信息列表
     */
    @PreAuthorize("@ss.hasPermi('program:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(RectificationInfo rectificationInfo)
    {
        startPage();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<RectificationInfo> list =tokenService.getLoginUserRectificationInfo(loginUser);
                //= rectificationInfoService.selectRectificationInfoList(rectificationInfo);
        return getDataTable(list);
    }

//    /**
//     * 导出整改信息列表
//     */
//    @PreAuthorize("@ss.hasPermi('program:info:export')")
//    @Log(title = "整改信息", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(RectificationInfo rectificationInfo)
//    {
//        List<RectificationInfo> list = rectificationInfoService.selectRectificationInfoList(rectificationInfo);
//        ExcelUtil<RectificationInfo> util = new ExcelUtil<RectificationInfo>(RectificationInfo.class);
//        return util.exportExcel(list, "info");
//    }

    /**
     * 获取整改信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('program:info:query')")
    @GetMapping(value = "/{riId}")
    public AjaxResult getInfo(@PathVariable("riId") Long riId)
    {
        return AjaxResult.success(rectificationInfoService.selectRectificationInfoById(riId));
    }

    /**
     * 新增整改信息
     */
    @PreAuthorize("@ss.hasPermi('program:info:add')")
    @Log(title = "整改信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RectificationInfo rectificationInfo)
    {
        return toAjax(rectificationInfoService.insertRectificationInfo(rectificationInfo));
    }

    /**
     * 修改整改信息
     */
    @PreAuthorize("@ss.hasPermi('program:info:edit')")
    @Log(title = "整改信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RectificationInfo rectificationInfo)
    {
        return toAjax(rectificationInfoService.updateRectificationInfo(rectificationInfo));
    }

    /**
     * 删除整改信息
     */
    @PreAuthorize("@ss.hasPermi('program:info:remove')")
    @Log(title = "整改信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{riIds}")
    public AjaxResult remove(@PathVariable Long[] riIds)
    {
        return toAjax(rectificationInfoService.deleteRectificationInfoByIds(riIds));
    }
}
