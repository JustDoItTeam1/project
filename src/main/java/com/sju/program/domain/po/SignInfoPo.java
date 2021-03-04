package com.sju.program.domain.po;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * signinfo
 * @author
 */
@Data
public class SignInfoPo implements Serializable {

    private String trafficicon;

    private String name;

    private Double longitude;

    private Double latitude;

    private String roadsection;

    private String detailedadd;

    private String jurisdictio;

    private String remark;

    private String projectName;

    private static final long serialVersionUID = 1L;
}
