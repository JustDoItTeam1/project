package com.sju.program.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.BuilderMapper;
import com.sju.program.domain.Builder;
import com.sju.program.service.IBuilderService;

/**
 * 施工单位Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class BuilderServiceImpl implements IBuilderService 
{
    @Autowired
    private BuilderMapper builderMapper;

    /**
     * 查询施工单位
     * 
     * @param builderId 施工单位ID
     * @return 施工单位
     */
    @Override
    public Builder selectBuilderById(Long builderId)
    {
        return builderMapper.selectBuilderById(builderId);
    }

    @Override
    public Builder selectBuilderByUsername(String username) {
        return builderMapper.selectBuilderByUsername(username);
    }

    /**
     * 查询施工单位列表
     * 
     * @param builder 施工单位
     * @return 施工单位
     */
    @Override
    public List<Builder> selectBuilderList(Builder builder)
    {
        return builderMapper.selectBuilderList(builder);
    }

    /**
     * 新增施工单位
     * 
     * @param builder 施工单位
     * @return 结果
     */
    @Override
    public int insertBuilder(Builder builder)
    {
        return builderMapper.insertBuilder(builder);
    }

    /**
     * 修改施工单位
     * 
     * @param builder 施工单位
     * @return 结果
     */
    @Override
    public int updateBuilder(Builder builder)
    {
        return builderMapper.updateBuilder(builder);
    }

    /**
     * 批量删除施工单位
     * 
     * @param builderIds 需要删除的施工单位ID
     * @return 结果
     */
    @Override
    public int deleteBuilderByIds(Long[] builderIds)
    {
        return builderMapper.deleteBuilderByIds(builderIds);
    }

    /**
     * 删除施工单位信息
     * 
     * @param builderId 施工单位ID
     * @return 结果
     */
    @Override
    public int deleteBuilderById(Long builderId)
    {
        return builderMapper.deleteBuilderById(builderId);
    }
}
