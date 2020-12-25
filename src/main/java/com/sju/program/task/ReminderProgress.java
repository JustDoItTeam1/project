package com.sju.program.task;

import com.sju.program.domain.Project;
import com.sju.program.domain.ProjectProcess;
import com.sju.program.domain.vo.NoticeToBuilder;
import com.sju.program.service.IProjectProcessService;
import com.sju.program.service.IProjectService;
import com.sju.program.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author qhw
 * @date 2020/12/15
 **/
@Component
public class ReminderProgress {
	@Autowired
	private IProjectProcessService projectProcessService;
	@Autowired
	private IProjectService projectService;

	@Scheduled(cron = "*/10 * * * * ?")
	public void reminder(){
		NoticeToBuilder noticeToBuilder=new NoticeToBuilder();
		Set<Integer> set=new HashSet<>();
		List<Integer> list=projectService.selectProjectProcessByTime();
		if (StringUtils.isNotEmpty(list)){
			int result=projectService.updateSubmiteprocessFlag(list);
			System.out.println(result);
		}
	}
}
