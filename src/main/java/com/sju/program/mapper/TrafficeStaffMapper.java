package com.sju.program.mapper;

import java.util.List;
import com.sju.program.domain.TrafficeStaff;
import org.apache.poi.ss.formula.functions.T;

/**
 * 交管人员Mapper接口
 *
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface TrafficeStaffMapper
{
    /**
     * 查询交管人员
     *
     * @param trafficId 交管人员ID
     * @return 交管人员
     */
    public TrafficeStaff selectTrafficeStaffById(Long trafficId);

    /**
     * 查询交管人员
     *
     * @param trafficUsername 交管人员名称
     * @return 交管人员
     */
    public TrafficeStaff selectTrafficeStaffByUsername(String trafficUsername);

    public List<TrafficeStaff> selectAllTrafficeStaffList();

    List<TrafficeStaff> selectTrafficeStaffByTrafficName(String name);


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
     * 删除交管人员
     *
     * @param trafficId 交管人员ID
     * @return 结果
     */
    public int deleteTrafficeStaffById(Long trafficId);

    /**
     * 批量删除交管人员
     *
     * @param trafficIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrafficeStaffByIds(Long[] trafficIds);

    public int saveTrafficeStaff(List<TrafficeStaff> trafficeStaffs);

    public int checkUserNameUnique(String name);

}
