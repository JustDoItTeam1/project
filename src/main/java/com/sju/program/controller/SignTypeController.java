package com.sju.program.controller;

import com.sju.program.domain.sign.SignType;
import com.sju.program.message.AjaxResult;
import com.sju.program.page.TableDataInfo;
import com.sju.program.service.SignTypeService;
import com.sju.program.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qhw
 * @date 2021/3/10
 **/
@Api(tags = "标牌类型管理接口")
@RestController
@RequestMapping("/program/signType")
public class SignTypeController extends BaseController{

	@Autowired
	private SignTypeService signTypeService;

	@ApiOperation(value = "查询标牌类型接口")
	@GetMapping("/list")
	public TableDataInfo selectAllSignType(){
		startPage();
		return getDataTable(signTypeService.selectAllSignType());
	}

	@ApiOperation(value = "删除标牌类型接口")
	@DeleteMapping("/{id}")
	public AjaxResult deleteSignType(@PathVariable Long[] id) throws Exception {
		return AjaxResult.success(signTypeService.deleteSignType(id));
	}

	@ApiOperation(value = "增加标牌类型接口")
	@PostMapping()
	public AjaxResult insertSignType(@RequestBody SignType signType){
		if (StringUtils.isNotNull(signTypeService.getSignTypeByName(signType.getName()))){
			return AjaxResult.error("该标牌类型已存在，新增失败");
		}
		return AjaxResult.success(signTypeService.insertSignType(signType));
	}

	@ApiOperation(value = "修改标牌类型接口")
	@PutMapping()
	public AjaxResult updateSignType(@RequestBody SignType signType){
		if (StringUtils.isNotNull(signTypeService.getSignTypeByName(signType.getName()))){
			return AjaxResult.error("该标牌类型已存在，新增失败");
		}
		return AjaxResult.success(signTypeService.updateSignType(signType));
	}
	@ApiOperation(value = "模糊查询")
	@GetMapping()
	public TableDataInfo search(@RequestParam("name") String name){
		startPage();
		return getDataTable(signTypeService.selectSignTypeByName(name));
	}

}
