package com.sju.program.mapper;

import java.util.List;
import com.sju.program.domain.ProjectProcess;

/**
 * 施工进度Mapper接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface ProjectProcessMapper 
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
     * 删除施工进度
     * 
     * @param ppId 施工进度ID
     * @return 结果
     */
    public int deleteProjectProcessById(Long ppId);

    /**
     * 批量删除施工进度
     * 
     * @param ppIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectProcessByIds(Long[] ppIds);
}
