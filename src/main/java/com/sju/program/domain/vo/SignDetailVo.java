package com.sju.program.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qhw
 * @date 2021/2/26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignDetailVo {

	@ApiModelProperty(value = "标牌ID")
	private Integer objectid;
	@ApiModelProperty(value = "标牌名称")
	private String name;
	@ApiModelProperty(value = "标牌经度")
	private Double longitude;
	@ApiModelProperty(value = "标牌维度")
	private Double latitude;
	@ApiModelProperty(value = "所在路段")
	private String roadsection;
	@ApiModelProperty(value = "详细位置")
	private String detailedadd;
	@ApiModelProperty(value = "管辖部门")
	private String jurisdictio;
	@ApiModelProperty(value = "备注")
	private String remark;


}
