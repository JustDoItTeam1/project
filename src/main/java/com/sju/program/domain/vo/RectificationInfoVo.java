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
public class RectificationInfoVo {

	/** 整改id */
	private Long riId;

	/** 项目名 */
	@Excel(name = "项目名")
	private String riProjectName;

	/** 整改截至时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "整改截至时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date riDate;

	/** 整改需求 */
	@Excel(name = "整改需求")
	private String riRequirements;

	/** 提交交警名 */
	@Excel(name = "提交交警名")
	private String riPoliceName;

	/** 现场照片地址 */
	@Excel(name = "现场照片地址")
	private String riPhotoPath;

	/** 整改状态 */
	@Excel(name = "整改状态")
	private String riStatus;

	/** 删除标识 */
	@Excel(name = "删除标识")
	private String riDeleteFlag;


	public RectificationInfoVo(Long riId, Date riDate, String riRequirements, String riPhotoPath, String riStatus, String riDeleteFlag) {
		this.riId = riId;
		this.riDate = riDate;
		this.riRequirements = riRequirements;
		this.riPhotoPath = riPhotoPath;
		this.riStatus = riStatus;
		this.riDeleteFlag = riDeleteFlag;
	}
}
