package com.sju.program.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.sju.program.domain.TrafficeStaff;
import com.sju.program.listener.PoliceListener;
import com.sju.program.listener.TrafficStaffListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.sju.program.domain.Police;
import com.sju.program.service.IPoliceService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 交警人员Controller
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Api(tags = "交警人员接口")
@RestController
@RequestMapping("/program/police")
public class PoliceController extends BaseController
{
    @Autowired
    private IPoliceService policeService;

    /**
     * 查询交警人员列表
     */
    @ApiOperation("查询交警人员列表")
    @PreAuthorize("@ss.hasPermi('program:police:list')")
    @GetMapping("/list")
    public TableDataInfo list(Police police)
    {
        startPage();
        List<Police> list = policeService.selectPoliceList(police);
        return getDataTable(list);
    }

    /**
     * 导出交管人员列表
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     **/
    @ApiOperation("导出交管人员列表")
    @PreAuthorize("@ss.hasPermi('program:police:export')")
    @Log(title = "交警人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("Police", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Police.class).sheet("人员").doWrite(policeService.selectAllPolice());
    }

    /**
     * 获取交警人员详细信息
     */
    @ApiOperation("获取id为x的交警人员详细信息")
    @PreAuthorize("@ss.hasPermi('program:police:query')")
    @GetMapping(value = "/{policeId}")
    public AjaxResult getInfo(@PathVariable("policeId") Long policeId)
    {
        return AjaxResult.success(policeService.selectPoliceById(policeId));
    }

    /**
     * 新增交警人员
     */
    @ApiOperation("新增交警人员")
    @PreAuthorize("@ss.hasPermi('program:police:add')")
    @Log(title = "交警人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Police police)
    {
        return toAjax(policeService.insertPolice(police));
    }

    /**
     * 修改交警人员
     */
    @ApiOperation("修改交警人员")
    @PreAuthorize("@ss.hasPermi('program:police:edit')")
    @Log(title = "交警人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Police police)
    {
        return toAjax(policeService.updatePolice(police));
    }

    /**
     * 删除交警人员
     */
    @ApiOperation("删除交警人员")
    @PreAuthorize("@ss.hasPermi('program:police:remove')")
    @Log(title = "交警人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{policeIds}")
    public AjaxResult remove(@PathVariable Long[] policeIds)
    {
        return toAjax(policeService.deletePoliceByIds(policeIds));
    }

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @ApiOperation("导入交警人员")
    @PreAuthorize("@ss.hasPermi('program:police:upload')")
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Police.class, new PoliceListener(policeService)).sheet().doRead();
        return AjaxResult.success();
    }
}
