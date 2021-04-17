package com.sju.program.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.vo.NoticeToBuilder;
import com.sju.program.domain.vo.ProjectProcessVo;
import com.sju.program.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.ProjectProcessMapper;
import com.sju.program.domain.ProjectProcess;
import com.sju.program.service.IProjectProcessService;

/**
 * 施工进度Service业务层处理
 *
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class ProjectProcessServiceImpl implements IProjectProcessService
{
    @Autowired
    private ProjectProcessMapper projectProcessMapper;

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 查询施工进度
     *
     * @param ppId 施工进度ID
     * @return 施工进度
     */
    @Override
    public ProjectProcessVo selectProjectProcessById(Long ppId)
    {
		ProjectProcess projectProcess=projectProcessMapper.selectProjectProcessById(ppId);
		ProjectProcessVo projectProcessVo=new ProjectProcessVo(projectProcess.getPpId(),projectProcess.getPpTime(),projectProcess.getPpDescription(),projectProcess.getPpDeleteFlag());
		projectProcessVo.setPpProjectName(projectMapper.selectProjectById(ppId).getProjectName());
        return projectProcessVo;
    }

    @Override
    public List<ProjectProcessVo> projectProcessVoList(List<ProjectProcess> list) {
        List<ProjectProcessVo> projectProcessVolist=new ArrayList<>();
        for(ProjectProcess projectProcess:list){
            ProjectProcessVo projectProcessVo=new ProjectProcessVo();
            projectProcessVo.setPpId(projectProcess.getPpId());
            projectProcessVo.setPpTime(projectProcess.getPpTime());
            projectProcessVo.setPpDescription(projectProcess.getPpDescription());
            projectProcessVo.setPpProjectName(projectMapper.selectProjectById(projectProcess.getPpProjectId()).getProjectName());
            projectProcessVo.setPpDeleteFlag(projectProcess.getPpDeleteFlag());
            projectProcessVolist.add(projectProcessVo);
        }
        return projectProcessVolist;
    }

    /**
     * 查询施工进度列表
     *
     * @param projectProcess 施工进度
     * @return 施工进度
     */
    @Override
    public List<ProjectProcess> selectProjectProcessList(ProjectProcess projectProcess)
    {
        return projectProcessMapper.selectProjectProcessList(projectProcess);
    }

    @Override
    public List<ProjectProcess> selectAllProjectProcessList() {
        return projectProcessMapper.selectAllProjectProcessList();
    }

    @Override
    public List<ProjectProcess> selectProjectProcessByBuilderId(Long builderId) {
        return projectProcessMapper.selectProjectProcessByBuilderId(builderId);
    }

    /**
     * 新增施工进度
     *
     * @param projectProcess 施工进度
     * @return 结果
     */
    @Override
    public int insertProjectProcess(ProjectProcess projectProcess)
    {
        return projectProcessMapper.insertProjectProcess(projectProcess);
    }

    /**
     * 修改施工进度
     *
     * @param projectProcess 施工进度
     * @return 结果
     */
    @Override
    public int updateProjectProcess(ProjectProcess projectProcess)
    {
        return projectProcessMapper.updateProjectProcess(projectProcess);
    }

    /**
     * 批量删除施工进度
     *
     * @param ppIds 需要删除的施工进度ID
     * @return 结果
     */
    @Override
    public int deleteProjectProcessByIds(Long[] ppIds)
    {
        return projectProcessMapper.updateProjectProcessByIds(ppIds);
    }

    /**
     * 删除施工进度信息
     *
     * @param ppId 施工进度ID
     * @return 结果
     */
    @Override
    public int deleteProjectProcessById(Long ppId)
    {
        return projectProcessMapper.deleteProjectProcessById(ppId);
    }

    @Override
    public List<NoticeToBuilder> selectProjectProcessByTime() {
        return projectProcessMapper.selectProjectProcessByTime();
    }

    @Override
    public List<ProjectProcessVo> selectProjectProcessByProjectName(BaseUser user, String projectName) {
        List<ProjectProcess> list=null;
        if (user.getAuthenticate()==4){
            list=projectProcessMapper.selectProjectProcessByProjectName(user.getId(),projectName);
        }else {
            list=projectProcessMapper.selectProjectProcessByProjectName02(projectName);
        }
        return projectProcessVoList(list);
    }
}
