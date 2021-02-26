package com.sju.program.domain.vo;

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
public class SigninfoVo {

	private Integer objectid;

	private String trafficicon2;

	private String name;

	private Double longitude;

	private Double latitude;

	//private String roadsection;

	private String detailedadd;

	private String maintenance;

	private String maintenance1;

	private String maintenance2;

	private String maintenance3;

	private String remark;

}
