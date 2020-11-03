package com.sju.program.service.impl;

import java.util.List;
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

    /**
     * 查询施工进度
     * 
     * @param ppId 施工进度ID
     * @return 施工进度
     */
    @Override
    public ProjectProcess selectProjectProcessById(Long ppId)
    {
        return projectProcessMapper.selectProjectProcessById(ppId);
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
        return projectProcessMapper.deleteProjectProcessByIds(ppIds);
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
}
