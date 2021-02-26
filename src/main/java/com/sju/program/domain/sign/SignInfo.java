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
    private Integer objectid;

    private Integer typeid;

    private String trafficicon;

    private String name;

    private Double longitude;

    private Double latitude;

    private String roadsection;

    private String detailedadd;

    private String jurisdictio;

    private Date createtime;

    private String constructco;

    private String constructco1;

    private String remark;

    private String searchstatu;

    private static final long serialVersionUID = 1L;
}
