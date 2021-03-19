package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.Project;
import com.sju.program.domain.vo.ProjectVo;
import io.swagger.models.auth.In;

/**
 * 施工项目Service接口
 *
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IProjectService
{
    /**
     * 查询施工项目
     *
     * @param projectId 施工项目ID
     * @return 施工项目
     */
    public ProjectVo selectProjectById(Long projectId);

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
     * 批量删除施工项目
     *
     * @param projectIds 需要删除的施工项目ID
     * @return 结果
     */
    public int deleteProjectByIds(Long[] projectIds);

    /**
     * 删除施工项目信息
     *
     * @param projectId 施工项目ID
     * @return 结果
     */
    public int deleteProjectById(Long projectId);

    public List<Object> selectProjectByName(String projectName);

    //public List<Object> searchProjectByLocation(String projectLocation);

    //public List<Object> searchProjectByManager(String projectManager);

    List<Project> selectProjectByBuilderId(Long id);

    List<Project> selectAllProjectList();

    List<Project> selectSiegeSchemeBySearch(List<Project> list,String projectInfo);

    List<Integer> selectProjectProcessByTime1();

    int updateSubmiteprocessFlag(List<Integer> list);

    List<Project> selectProjectListBySubmiteprocessFlag();

    String unique(String name);


}
