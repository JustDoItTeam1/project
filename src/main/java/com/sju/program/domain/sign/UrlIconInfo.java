package com.sju.program.domain.sign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * urliconinfo
 * @author
 */
@ApiModel(value="com.sju.program.domain.sign.Urliconinfo")
@Data
public class UrlIconInfo implements Serializable {

    private String trafficicon;

    private String trafficicon1;

    private String trafficicon2;

    private static final long serialVersionUID = 1L;
}
