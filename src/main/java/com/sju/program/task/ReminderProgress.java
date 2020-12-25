package com.sju.program.task;

import com.sju.program.domain.ProjectProcess;
import com.sju.program.service.IProjectProcessService;
import com.sju.program.service.IProjectService;
import com.sju.program.utils.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Test
	@Scheduled(cron = "0 0 23 0 1/2 ? ")
	public void reminder(){
		Set<Integer> set=new HashSet<>();
		List<ProjectProcess> list=projectProcessService.selectProjectProcessByTime();
		if (StringUtils.isNotEmpty(list)){

		}

	}
}