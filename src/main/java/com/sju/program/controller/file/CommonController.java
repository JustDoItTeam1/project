package com.sju.program.controller.file;

import com.sju.program.config.ProgramConfig;
import com.sju.program.constant.Constants;
import com.sju.program.message.AjaxResult;
import com.sju.program.utils.StringUtils;
import com.sju.program.utils.file.FileUploadUtils;
import com.sju.program.utils.file.FileUtils;
import com.sju.program.config.ServerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Api(tags = "文件上传下载接口")
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     *
     */
    @ApiOperation(value = "下载请求",response = AjaxResult.class)
    //@PreAuthorize("@ss.hasPermi('enclosure:scheme:download')")
    @GetMapping("common/download")
    public AjaxResult fileDownload(String fileName,  HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
//            if (!FileUtils.isValidFilename(fileName))
//            {
//                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
//            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String windowsFileName=fileName.replace("/","\\");
            String filePath = Constants.WINDOWS_FILE_PATH + windowsFileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            return AjaxResult.success("下载成功");
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
            return AjaxResult.error("下载失败");

        }
    }

    /**
     * 通用上传请求
     */
    @ApiOperation("上传请求")
    //@PreAuthorize("@ss.hasPermi('enclosure:scheme:list')")
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath =Constants.WINDOWS_FILE_PATH;
            // 上传并返回新文件名称(相对路径)
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            //ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

//    /**
//     * 本地资源通用下载
//     */
//    @GetMapping("/common/download/resource")
//    public void resourceDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
//        // 本地资源路径
//        String localPath = Constants.WINDOWS_FILE_PATH;
//        // 数据库资源地址
//        String downloadPath = localPath + StringUtils.substringAfter(name, Constants.RESOURCE_PREFIX);
//        // 下载名称
//        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition",
//                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
//        FileUtils.writeBytes(downloadPath, response.getOutputStream());
//    }
}
