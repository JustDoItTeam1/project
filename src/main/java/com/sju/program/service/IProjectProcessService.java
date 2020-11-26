package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.ProjectProcess;

/**
 * 施工进度Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IProjectProcessService 
{
    /**
     * 查询施工进度
     * 
     * @param ppId 施工进度ID
     * @return 施工进度
     */
    public ProjectProcess selectProjectProcessById(Long ppId);

    /**
     * 查询施工进度列表
     * 
     * @param projectProcess 施工进度
     * @return 施工进度集合
     */
    public List<ProjectProcess> selectProjectProcessList(ProjectProcess projectProcess);

    public List<ProjectProcess> selectAllProjectProcessList();

    public List<ProjectProcess> selectProjectProcessByBuilderId(Long builderId);





    /**
     * 新增施工进度
     * 
     * @param projectProcess 施工进度
     * @return 结果
     */
    public int insertProjectProcess(ProjectProcess projectProcess);

    /**
     * 修改施工进度
     * 
     * @param projectProcess 施工进度
     * @return 结果
     */
    public int updateProjectProcess(ProjectProcess projectProcess);

    /**
     * 批量删除施工进度
     * 
     * @param ppIds 需要删除的施工进度ID
     * @return 结果
     */
    public int deleteProjectProcessByIds(Long[] ppIds);

    /**
     * 删除施工进度信息
     * 
     * @param ppId 施工进度ID
     * @return 结果
     */
    public int deleteProjectProcessById(Long ppId);
}
