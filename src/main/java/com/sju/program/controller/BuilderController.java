package com.sju.program.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.sju.program.constant.UserConstants;
import com.sju.program.domain.Police;
import com.sju.program.listener.BuilderListener;
import com.sju.program.listener.PoliceListener;
import com.sju.program.utils.SecurityUtils;
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
import com.sju.program.domain.Builder;
import com.sju.program.service.IBuilderService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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


    /**
     * 导出交警人员列表
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     **/
    @ApiOperation("导出施工单位人员列表")
   // @PreAuthorize("@ss.hasPermi('program:builder:export')")
    @Log(title = "施工单位", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("施工单位人员列表", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), Builder.class).sheet("人员").doWrite(builderService.selectAllBuilder());
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

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
        if (UserConstants.NOT_UNIQUE.equals(builderService.checkUserNameUnique(builder.getBuilderName()))){
            return AjaxResult.error("新增失败，登录账号已存在");
        }
        builder.setBuilderPassword(SecurityUtils.encryptPassword(builder.getUserPassword()));
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

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @ApiOperation("导入施工单位人员")
   // @PreAuthorize("@ss.hasPermi('program:builder:upload')")
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Builder.class, new BuilderListener(builderService)).sheet().doRead();
        return AjaxResult.success();
    }
}
