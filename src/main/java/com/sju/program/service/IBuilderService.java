package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.Builder;

/**
 * 施工单位Service接口
 *
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IBuilderService
{
    /**
     * 查询施工单位
     *
     * @param builderId 施工单位ID
     * @return 施工单位
     */
    public Builder selectBuilderById(Long builderId);


    public Builder selectBuilderByUsername(String username);

    /**
     * 查询施工单位列表
     *
     * @param builder 施工单位
     * @return 施工单位集合
     */
    public List<Builder> selectBuilderList(Builder builder);

    public List<Builder> selectAllBuilder();

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
     * 批量删除施工单位
     *
     * @param builderIds 需要删除的施工单位ID
     * @return 结果
     */
    public int deleteBuilderByIds(Long[] builderIds);

    /**
     * 删除施工单位信息
     *
     * @param builderId 施工单位ID
     * @return 结果
     */
    public int deleteBuilderById(Long builderId);

    public int save(List<Builder> list);

    public String checkUserNameUnique(String builderName);

    List<Builder> selectBuilderBybuilderNameAndUseranme(String builderName, String useranme);
}
