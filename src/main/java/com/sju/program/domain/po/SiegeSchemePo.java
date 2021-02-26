package com.sju.program.domain.po;

import com.sju.program.domain.SiegeScheme;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author qhw
 * @date 2021/2/26
 **/
@Data
@Builder
public class SiegeSchemePo {

	private Long ssBuilderId;

	private Long ssProjectId;

	private List<SiegeScheme> stage;
}
