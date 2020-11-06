package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.RectificationInfo;

/**
 * 整改信息Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IRectificationInfoService 
{
    /**
     * 查询整改信息
     * 
     * @param riId 整改信息ID
     * @return 整改信息
     */
    public RectificationInfo selectRectificationInfoById(Long riId);

    public List<RectificationInfo> selectRectificationInfoByBuilderId(Long riId);



    /**
     * 查询整改信息列表
     * 
     * @param rectificationInfo 整改信息
     * @return 整改信息集合
     */
    public List<RectificationInfo> selectRectificationInfoList(RectificationInfo rectificationInfo);

    public List<RectificationInfo> selectRectificationInfoList();

    /**
     * 新增整改信息
     * 
     * @param rectificationInfo 整改信息
     * @return 结果
     */
    public int insertRectificationInfo(RectificationInfo rectificationInfo);

    /**
     * 修改整改信息
     * 
     * @param rectificationInfo 整改信息
     * @return 结果
     */
    public int updateRectificationInfo(RectificationInfo rectificationInfo);

    /**
     * 批量删除整改信息
     * 
     * @param riIds 需要删除的整改信息ID
     * @return 结果
     */
    public int deleteRectificationInfoByIds(Long[] riIds);

    /**
     * 删除整改信息信息
     * 
     * @param riId 整改信息ID
     * @return 结果
     */
    public int deleteRectificationInfoById(Long riId);
}
