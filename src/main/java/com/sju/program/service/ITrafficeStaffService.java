package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.TrafficeStaff;

/**
 * 交管人员Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface ITrafficeStaffService 
{
    /**
     * 查询交管人员
     * 
     * @param trafficId 交管人员ID
     * @return 交管人员
     */
    public TrafficeStaff selectTrafficeStaffById(Long trafficId);


    public List<TrafficeStaff> selectAllTrafficeStaffList();



    /**
     * 查询交管人员
     *
     * @param trafficUseranme 交管人员名称
     * @return 交管人员
     */
    public TrafficeStaff selectTrafficeStaffByUsername(String trafficUseranme);



    /**
     * 查询交管人员列表
     * 
     * @param trafficeStaff 交管人员
     * @return 交管人员集合
     */
    public List<TrafficeStaff> selectTrafficeStaffList(TrafficeStaff trafficeStaff);

    /**
     * 新增交管人员
     * 
     * @param trafficeStaff 交管人员
     * @return 结果
     */
    public int insertTrafficeStaff(TrafficeStaff trafficeStaff);

    /**
     * 修改交管人员
     * 
     * @param trafficeStaff 交管人员
     * @return 结果
     */
    public int updateTrafficeStaff(TrafficeStaff trafficeStaff);

    /**
     * 批量删除交管人员
     * 
     * @param trafficIds 需要删除的交管人员ID
     * @return 结果
     */
    public int deleteTrafficeStaffByIds(Long[] trafficIds);

    /**
     * 删除交管人员信息
     * 
     * @param trafficId 交管人员ID
     * @return 结果
     */
    public int deleteTrafficeStaffById(Long trafficId);

    public int save(List<TrafficeStaff> trafficeStaffs);
}
