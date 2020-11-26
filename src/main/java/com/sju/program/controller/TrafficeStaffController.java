package com.sju.program.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.sju.program.listener.TrafficStaffListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sju.program.annotation.Log;
import com.sju.program.message.AjaxResult;
import com.sju.program.enums.BusinessType;
import com.sju.program.page.TableDataInfo;
import com.sju.program.domain.TrafficeStaff;
import com.sju.program.service.ITrafficeStaffService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 交管人员Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Api(tags = "交管人员接口")
@RestController
@RequestMapping("/program/staff")
public class TrafficeStaffController extends BaseController
{
    @Autowired
    private ITrafficeStaffService trafficeStaffService;

    /**
     * 查询交管人员列表
     */
    @ApiOperation("查询人员列表")
    @PreAuthorize("@ss.hasPermi('program:staff:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrafficeStaff trafficeStaff)
    {
        startPage();
        List<TrafficeStaff> list = trafficeStaffService.selectTrafficeStaffList(trafficeStaff);
        return getDataTable(list);
    }

    /**
     * 导出交管人员列表
    * 文件下载（失败了会返回一个有部分数据的Excel）
    * <p>1. 创建excel对应的实体对象
    * <p>2. 设置返回的 参数
    * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
    **/
    @ApiOperation("导出人员列表")
    @PreAuthorize("@ss.hasPermi('program:staff:export')")
    @Log(title = "交管人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("trafficeStaff", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), TrafficeStaff.class).sheet("人员").doWrite(trafficeStaffService.selectAllTrafficeStaffList());
    }

    /**
     * 获取交管人员详细信息
     */
    @ApiOperation("获取id为x的人员")
    @PreAuthorize("@ss.hasPermi('program:staff:query')")
    @GetMapping(value = "/{trafficId}")
    public AjaxResult getInfo(@PathVariable("trafficId") Long trafficId)
    {
        return AjaxResult.success(trafficeStaffService.selectTrafficeStaffById(trafficId));
    }

    /**
     * 新增交管人员
     */
    @ApiOperation("新增交管人员")
    @PreAuthorize("@ss.hasPermi('program:staff:add')")
    @Log(title = "交管人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrafficeStaff trafficeStaff)
    {
        return toAjax(trafficeStaffService.insertTrafficeStaff(trafficeStaff));
    }

    /**
     * 修改交管人员
     */
    @ApiOperation("修改交管人员")
    @PreAuthorize("@ss.hasPermi('program:staff:edit')")
    @Log(title = "交管人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrafficeStaff trafficeStaff)
    {
        return toAjax(trafficeStaffService.updateTrafficeStaff(trafficeStaff));
    }

    /**
     * 删除交管人员
     */
    @ApiOperation("删除交管人员")
    @PreAuthorize("@ss.hasPermi('program:staff:remove')")
    @Log(title = "交管人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{trafficIds}")
    public AjaxResult remove(@PathVariable Long[] trafficIds)
    {
        return toAjax(trafficeStaffService.deleteTrafficeStaffByIds(trafficIds));
    }

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @ApiOperation("导入交管人员")
    @PreAuthorize("@ss.hasPermi('program:staff:upload')")
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), TrafficeStaff.class, new TrafficStaffListener(trafficeStaffService)).sheet().doRead();
        return AjaxResult.success();
    }
}
