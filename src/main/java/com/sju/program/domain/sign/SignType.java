package com.sju.program.domain.sign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * sign_type
 * @author 
 */
@ApiModel(value="com.sju.program.domain.sign.SignType")
@Data
public class SignType implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 标牌类型名称
     */
    @ApiModelProperty(value="标牌类型名称")
    private String name;

    private static final long serialVersionUID = 1L;
}