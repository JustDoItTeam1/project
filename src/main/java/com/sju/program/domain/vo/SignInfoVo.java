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
public class SignInfoVo {

	private Integer id;

	private String name;

	private String icon;

	private String type;

	private Double longitude;

	private Double latitude;

	private String roadsection;

	private String detailedadd;

	private String jurisdictio;

	private Date createTime;

	private String remark;

	private String projectName;

}
