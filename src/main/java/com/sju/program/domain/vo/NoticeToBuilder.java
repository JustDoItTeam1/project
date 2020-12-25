package com.sju.program.domain.vo;

import com.sju.program.domain.Builder;
import com.sju.program.domain.Project;
import lombok.Data;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author qhw
 * @date 2020/12/25
 **/
@Data
public class NoticeToBuilder {

	private List<Project> list;
}
