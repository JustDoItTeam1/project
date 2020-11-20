package com.sju.program.service.impl;

import java.util.ArrayList;
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
    public Project selectProjectById(Long projectId)
    {
        return projectMapper.selectProjectById(projectId);
    }

    @Override
    public List<Object> selectProjectByName(String projectName)
    {
        return projectMapper.selectProjectByName(projectName);
    }

    @Override
    public List<Project> selectProjectByBuilderId(Long builderId) {
        return projectMapper.selectProjectByBuilderId(builderId);
    }

    @Override
    public List<Project> selectAllProjectList() {
        return projectMapper.selectAllProjectList();
    }

    @Override
    public List<Project> selectSiegeSchemeBySearch(List<Project> list,String projectInfo) {
        List<Project> projectList=new ArrayList<>();
        for(Project project:list){
            if(project.getProjectBuilderName().contains(projectInfo)||project.getProjectName().contains(projectInfo)){
                projectList.add(project);
            }
        }
        return projectList;
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
