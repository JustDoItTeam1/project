package com.sju.program.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sju.program.domain.ProjectProcess;
import com.sju.program.domain.vo.ProjectProcessVo;
import com.sju.program.domain.vo.RectificationInfoVo;
import com.sju.program.mapper.PoliceMapper;
import com.sju.program.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.RectificationInfoMapper;
import com.sju.program.domain.RectificationInfo;
import com.sju.program.service.IRectificationInfoService;

/**
 * 整改信息Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class RectificationInfoServiceImpl implements IRectificationInfoService 
{
    @Autowired
    private RectificationInfoMapper rectificationInfoMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private PoliceMapper policeMapper;

    /**
     * 查询整改信息
     * 
     * @param riId 整改信息ID
     * @return 整改信息
     */
    @Override
    public RectificationInfoVo selectRectificationInfoById(Long riId)
    {
        RectificationInfo rectificationInfo=rectificationInfoMapper.selectRectificationInfoById(riId);
        RectificationInfoVo rectificationInfoVo=new RectificationInfoVo(rectificationInfo.getRiId(),rectificationInfo.getRiDate(),rectificationInfo.getRiRequirements(),rectificationInfo.getRiPhotoPath(),rectificationInfo.getRiStatus(),rectificationInfo.getRiDeleteFlag());
        rectificationInfoVo.setRiProjectName(projectMapper.selectProjectById(rectificationInfo.getRiProjectId()).getProjectName());
        rectificationInfoVo.setRiPoliceName(policeMapper.selectPoliceById(rectificationInfo.getRiPoliceId()).getPoliceName());
        return rectificationInfoVo;
    }

    /**
     * 查询整改信息列表
     * 
     * @param rectificationInfo 整改信息
     * @return 整改信息
     */
    @Override
    public List<RectificationInfo> selectRectificationInfoList(RectificationInfo rectificationInfo)
    {
        return rectificationInfoMapper.selectRectificationInfoList(rectificationInfo);
    }

    @Override
    public List<RectificationInfo> selectRectificationInfoByBuilderId(Long riId) {
        return rectificationInfoMapper.selectRectificationInfoByBuilderId(riId);
    }

    @Override
    public List<RectificationInfo> selectRectificationInfoList() {
        return rectificationInfoMapper.selectRectificationInfoList();
    }

    @Override
    public int confirmRectificationInfo(Long riId) {
        return rectificationInfoMapper.confirmRectificationInfo(riId);
    }

    @Override
    public List<RectificationInfoVo> rectificationInfoVoList(List<RectificationInfo> list) {
        List<RectificationInfoVo> rectificationInfoVoList=new ArrayList<>();
        for(RectificationInfo rectificationInfo:list){
            RectificationInfoVo rectificationInfoVo=new RectificationInfoVo(rectificationInfo.getRiId(),rectificationInfo.getRiDate(),rectificationInfo.getRiRequirements(),rectificationInfo.getRiPhotoPath(),rectificationInfo.getRiStatus(),rectificationInfo.getRiDeleteFlag());
            rectificationInfoVo.setRiProjectName(projectMapper.selectProjectById(rectificationInfo.getRiProjectId()).getProjectName());
            rectificationInfoVo.setRiPoliceName(policeMapper.selectPoliceById(rectificationInfo.getRiPoliceId()).getPoliceName());
            rectificationInfoVoList.add(rectificationInfoVo);
        }

        return rectificationInfoVoList;
    }

    /**
     * 新增整改信息
     * 
     * @param rectificationInfo 整改信息
     * @return 结果
     */
    @Override
    public int insertRectificationInfo(RectificationInfo rectificationInfo)
    {
        return rectificationInfoMapper.insertRectificationInfo(rectificationInfo);
    }

    /**
     * 修改整改信息
     * 
     * @param rectificationInfo 整改信息
     * @return 结果
     */
    @Override
    public int updateRectificationInfo(RectificationInfo rectificationInfo)
    {
        return rectificationInfoMapper.updateRectificationInfo(rectificationInfo);
    }

    /**
     * 批量删除整改信息
     * 
     * @param riIds 需要删除的整改信息ID
     * @return 结果
     */
    @Override
    public int deleteRectificationInfoByIds(Long[] riIds)
    {
        return rectificationInfoMapper.updateRectificationDeleteFlagInfoByIds(riIds);
    }

    /**
     * 删除整改信息信息
     * 
     * @param riId 整改信息ID
     * @return 结果
     */
    @Override
    public int deleteRectificationInfoById(Long riId)
    {
        return rectificationInfoMapper.deleteRectificationInfoById(riId);
    }
}
