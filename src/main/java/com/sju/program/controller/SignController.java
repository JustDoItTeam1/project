package com.sju.program.controller;

import com.sju.program.domain.Project;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.Sign;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SignInfoVo;
import com.sju.program.message.AjaxResult;
import com.sju.program.page.TableDataInfo;
import com.sju.program.service.SignService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.ServletUtils;
import com.sju.program.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@Autowired
	private TokenService tokenService;


	@ApiOperation(value = "查询地图标牌接口")
	@GetMapping("/signInfo/list")
	public TableDataInfo getAllSignInfo(){
		startPage();
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		List<SignInfoVo> signInfoVos = tokenService.getLoginUserSignInfo(loginUser);
		return getDataTable(signInfoVos);
	}
	@ApiOperation(value = "查询标牌接口")
	@GetMapping("/list")
	public TableDataInfo getAllSign(){
		startPage();
		return getDataTable(service.getAllSign());
	}
	@ApiOperation(value = "查询单个标牌接口")
	@GetMapping("/{id}")
	public AjaxResult getSignById(@PathVariable("id") Long id){
		return AjaxResult.success(service.getSignById(id));
	}
	@ApiOperation(value = "增加标牌接口")
	@PostMapping()
	public AjaxResult insertSign(@RequestBody Sign sign){
		if (StringUtils.isNotNull(service.getSignByName(sign.getName()))){
			return AjaxResult.error("该标牌已存在，新增失败");
		}
		return AjaxResult.success(service.insertSign(sign));
	}

	@ApiOperation(value = "修改标牌接口")
	@PutMapping()
	public AjaxResult updateSign(@RequestBody Sign sign){
		if (StringUtils.isNotNull(service.getSignByName(sign.getName()))){
			return AjaxResult.error("该标牌已存在，修改失败");
		}
		return AjaxResult.success(service.updateSign(sign));
	}

	@ApiOperation(value = "删除标牌接口")
	@DeleteMapping("/{id}")
	public AjaxResult deleteSign(@PathVariable Long[] id) throws Exception{
		return AjaxResult.success(service.deleteSign(id));
	}

	@ApiOperation(value = "增加地图标牌")
	@PostMapping("/inMap")
	public AjaxResult insertSignInMap(@RequestBody SignInfoPo signInfoPo){
		if (StringUtils.isNotNull(service.getSignInfoByLongitudeAndLatitude(signInfoPo))){
			return AjaxResult.error("新增失败，该地点已有标牌");
		}
		return AjaxResult.success(service.insertSignInfo(signInfoPo));
	}
	@ApiOperation(value = "查询单个地图标牌")
	@GetMapping("/inMap/{id}")
	public AjaxResult selectSignInMapById(@PathVariable("id") Long id){
		return AjaxResult.success(service.selectSignInMapById(id));
	}

	@ApiOperation(value = "删除地图标牌")
	@DeleteMapping("/signInfo/{id}")
	public AjaxResult deleteSignInfo(@PathVariable Long[] id){
		return AjaxResult.success(service.deleteSignInfo(id));
	}
}
