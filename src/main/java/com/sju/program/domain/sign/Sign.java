package com.sju.program.domain.sign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * sign
 * @author 
 */
@ApiModel(value="com.sju.program.domain.sign.Sign")
@Data
public class Sign implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Long signId;

    /**
     * 标牌名称
     */
    @ApiModelProperty(value="标牌名称")
    private String name;

    /**
     * 标牌图片url
     */
    @ApiModelProperty(value="标牌图片url")
    private String icon;

    /**
     * 标牌类型
     */
    @ApiModelProperty(value="标牌类型")
    private String type;

    private static final long serialVersionUID = 1L;
}