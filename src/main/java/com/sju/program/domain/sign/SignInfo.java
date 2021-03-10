package com.sju.program.domain.sign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sign_info
 * @author 
 */
@ApiModel(value="com.sju.program.domain.sign.SignInfo")
@Data
public class SignInfo implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 标牌id
     */
    @ApiModelProperty(value="标牌id")
    private Long signId;

    /**
     * 经度
     */
    @ApiModelProperty(value="经度")
    private Double longitude;

    /**
     * 维度
     */
    @ApiModelProperty(value="维度")
    private Double latitude;

    /**
     * 交通标志所处路段
     */
    @ApiModelProperty(value="交通标志所处路段")
    private String roadsection;

    /**
     * 交通标志所处的详细地址
     */
    @ApiModelProperty(value="交通标志所处的详细地址")
    private String detailedadd;

    /**
     * 交通标志的管辖部门
     */
    @ApiModelProperty(value="交通标志的管辖部门")
    private String jurisdictio;

    /**
     * 标牌的创建时间
     */
    @ApiModelProperty(value="标牌的创建时间")
    private Date createtime;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 标牌对应的项目名
     */
    @ApiModelProperty(value="标牌对应的项目名")
    private String projectName;

    private static final long serialVersionUID = 1L;
}