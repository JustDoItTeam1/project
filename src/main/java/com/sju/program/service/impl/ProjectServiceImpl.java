package com.sju.program.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.ProjectMapper;
import com.sju.program.domain.Project;
import com.sju.program.service.IProjectService;

/**
 * 施工项目Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class ProjectServiceImpl implements IProjectService 
{
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 查询施工项目
     * 
     * @param projectId 施工项目ID
     * @return 施工项目
     */
    @Override
    public List<Project> selectProjectById(Long projectId,Long builderId)
    {
        return projectMapper.selectProjectById(projectId,builderId);
    }

    @Override
    public List<Object> selectProjectByName(String projectName)
    {
        return projectMapper.selectProjectByName(projectName);
    }

    @Override
    public List<Object> selectProjectByBuilder(String projectBuilderName) {
        return projectMapper.selectProjectByBuilder(projectBuilderName);
    }

    @Override
    public List<Object> selectProjectByManger(String projectManger) {
        return projectMapper.selectProjectByManger(projectManger);
    }

    @Override
    public List<Project> selectAllProjectList() {
        return projectMapper.selectAllProjectList();
    }

    @Override
    public List<Project> selectProject(Long projectId) {
        return projectMapper.selectProject(projectId);
    }

    /**
     * 查询施工项目列表
     * 
     * @param project 施工项目
     * @return 施工项目
     */
    @Override
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增施工项目
     * 
     * @param project 施工项目
     * @return 结果
     */
    @Override
    public int insertProject(Project project)
    {
        return projectMapper.insertProject(project);
    }

    /**
     * 修改施工项目
     * 
     * @param project 施工项目
     * @return 结果
     */
    @Override
    public int updateProject(Project project)
    {
        return projectMapper.updateProject(project);
    }

    /**
     * 批量删除施工项目
     * 
     * @param projectIds 需要删除的施工项目ID
     * @return 结果
     */
    @Override
    public int deleteProjectByIds(Long[] projectIds)
    {
        return projectMapper.deleteProjectByIds(projectIds);
    }

    /**
     * 删除施工项目信息
     * 
     * @param projectId 施工项目ID
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long projectId)
    {
        return projectMapper.deleteProjectById(projectId);
    }
}
