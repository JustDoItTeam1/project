package com.sju.program.controller;

import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.message.AjaxResult;
import com.sju.program.page.TableDataInfo;
import com.sju.program.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qhw
 * @date 2021/2/26
 **/
@Api(tags = "标牌管理接口")
@RestController
@RequestMapping("/program/sign")
public class SignController extends BaseController{

	@Autowired
	private SignService service;
	@ApiOperation(value = "查询标牌接口")
	@GetMapping("/list")
	public TableDataInfo getAllSignInfo(){
		startPage();
		return getDataTable(service.getAllSign());
	}
//
//	@ApiOperation(value = "查询某个标牌的详细信息")
//	@GetMapping("/{id}")
//	public AjaxResult getDetailById(@PathVariable("id") String id){
//		return AjaxResult.success(service.getDetailById(id));
//	}

	@ApiOperation(value = "增加标牌")
	@PostMapping()
	public AjaxResult insertSign(@RequestBody SignInfoPo signInfo){
		return AjaxResult.success(service.insertSign(signInfo));
	}
}
