package com.sju.program.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sju.program.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qhw
 * @date 2020/12/10
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectProcessVo {

	/** 施工进度id */
	private Long ppId;

	/** 填写时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "填写时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date ppTime;

	/** 项目name */
	@Excel(name = "项目name")
	private String ppProjectName;

	/** 施工进度描述 */
	@Excel(name = "施工进度描述")
	private String ppDescription;

	/** 删除标识 */
	@Excel(name = "删除标识")
	private String ppDeleteFlag;


	public ProjectProcessVo(Long ppId, Date ppTime,  String ppDescription, String ppDeleteFlag) {
		this.ppId = ppId;
		this.ppTime = ppTime;
		this.ppDescription = ppDescription;
		this.ppDeleteFlag = ppDeleteFlag;
	}
}
