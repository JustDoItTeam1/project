package com.sju.program.domain.sign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * signinfo
 * @author
 */
@ApiModel(value="com.sju.program.domain.sign.Signinfo")
@Data
public class SignInfo implements Serializable {

    private Long id;

    private String trafficicon;

    private String name;

    private Double longitude;

    private Double latitude;

    private String roadsection;

    private String detailedadd;

    private String jurisdictio;

    private Date createtime;

    private String remark;

    private String projectName;

    private static final long serialVersionUID = 1L;
}
