package com.sju.program.mapper;

import java.util.List;
import com.sju.program.domain.Builder;

/**
 * 施工单位Mapper接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface BuilderMapper 
{
    /**
     * 查询施工单位
     * 
     * @param builderId 施工单位ID
     * @return 施工单位
     */
    public Builder selectBuilderById(Long builderId);
    /**
     * 查询施工单位
     *
     * @param builderName 施工单位ID
     * @return 施工单位
     */

    public Builder selectBuilderByUsername(String builderName);


    /**
     * 查询施工单位列表
     * 
     * @param builder 施工单位
     * @return 施工单位集合
     */
    public List<Builder> selectBuilderList(Builder builder);

    public List<Builder> selectAllBuilderList();

    List<Builder> selectBuilderByBuilderId(long builderId);

    /**
     * 新增施工单位
     * 
     * @param builder 施工单位
     * @return 结果
     */
    public int insertBuilder(Builder builder);

    /**
     * 修改施工单位
     * 
     * @param builder 施工单位
     * @return 结果
     */
    public int updateBuilder(Builder builder);

    /**
     * 删除施工单位
     * 
     * @param builderId 施工单位ID
     * @return 结果
     */
    public int deleteBuilderById(Long builderId);

    /**
     * 批量删除施工单位
     * 
     * @param builderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBuilderByIds(Long[] builderIds);


}
