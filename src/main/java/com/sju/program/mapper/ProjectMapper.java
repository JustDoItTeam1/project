package com.sju.program.mapper;

import java.util.List;
import com.sju.program.domain.Project;

/**
 * 施工项目Mapper接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface ProjectMapper 
{
    /**
     * 查询施工项目
     * 
     * @param projectId 施工项目ID
     * @return 施工项目
     */
    public Project selectProjectById(Long projectId);

    public List<Project> selectProjectByBuilderId(Long builderId);

    /**
     * 查询施工项目列表
     * 
     * @param project 施工项目
     * @return 施工项目集合
     */
    public List<Project> selectProjectList(Project project);

    /**
     * 新增施工项目
     * 
     * @param project 施工项目
     * @return 结果
     */
    public int insertProject(Project project);

    /**
     * 修改施工项目
     * 
     * @param project 施工项目
     * @return 结果
     */
    public int updateProject(Project project);

    /**
     * 删除施工项目
     * 
     * @param projectId 施工项目ID
     * @return 结果
     */
    public int deleteProjectById(Long projectId);

    /**
     * 批量删除施工项目
     * 
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectByIds(Long[] projectIds);
}
