package com.sju.program.service.impl;

import java.util.List;

import com.sju.program.constant.UserConstants;
import com.sju.program.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.TrafficeStaffMapper;
import com.sju.program.domain.TrafficeStaff;
import com.sju.program.service.ITrafficeStaffService;

/**
 * 交管人员Service业务层处理
 *
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class TrafficeStaffServiceImpl implements ITrafficeStaffService
{
    @Autowired
    private TrafficeStaffMapper trafficeStaffMapper;

    /**
     * 查询交管人员
     *
     * @param trafficId 交管人员ID
     * @return 交管人员
     */
    @Override
    public TrafficeStaff selectTrafficeStaffById(Long trafficId)
    {
        return trafficeStaffMapper.selectTrafficeStaffById(trafficId);
    }

    @Override
    public TrafficeStaff selectTrafficeStaffByUsername(String trafficUseranme) {
        return trafficeStaffMapper.selectTrafficeStaffByUsername(trafficUseranme);
    }

    @Override
    public List<TrafficeStaff> selectAllTrafficeStaffList() {
        return trafficeStaffMapper.selectAllTrafficeStaffList();
    }

    @Override
    public String checkUserNameUnique(String name) {
        int count= trafficeStaffMapper.checkUserNameUnique(name);
        if (count>0){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 查询交管人员列表
     *
     * @param trafficeStaff 交管人员
     * @return 交管人员
     */
    @Override
    public List<TrafficeStaff> selectTrafficeStaffList(TrafficeStaff trafficeStaff)
    {
        return trafficeStaffMapper.selectTrafficeStaffList(trafficeStaff);
    }

    /**
     * 新增交管人员
     *
     * @param trafficeStaff 交管人员
     * @return 结果
     */
    @Override
    public int insertTrafficeStaff(TrafficeStaff trafficeStaff)
    {
        return trafficeStaffMapper.insertTrafficeStaff(trafficeStaff);
    }

    /**
     * 修改交管人员
     *
     * @param trafficeStaff 交管人员
     * @return 结果
     */
    @Override
    public int updateTrafficeStaff(TrafficeStaff trafficeStaff)
    {
        trafficeStaff.setTrafficPassword(SecurityUtils.encryptPassword(trafficeStaff.getTrafficPassword()));
        return trafficeStaffMapper.updateTrafficeStaff(trafficeStaff);
    }

    /**
     * 批量删除交管人员
     *
     * @param trafficIds 需要删除的交管人员ID
     * @return 结果
     */
    @Override
    public int deleteTrafficeStaffByIds(Long[] trafficIds)
    {
        return trafficeStaffMapper.deleteTrafficeStaffByIds(trafficIds);
    }

    /**
     * 删除交管人员信息
     *
     * @param trafficId 交管人员ID
     * @return 结果
     */
    @Override
    public int deleteTrafficeStaffById(Long trafficId)
    {
        return trafficeStaffMapper.deleteTrafficeStaffById(trafficId);
    }

    @Override
    public int save(List<TrafficeStaff> trafficeStaffs) {
        return trafficeStaffMapper.saveTrafficeStaff(trafficeStaffs);
    }

    @Override
    public List<TrafficeStaff> selectTrafficeStaffByTrafficName(String name) {
        return trafficeStaffMapper.selectTrafficeStaffByTrafficName(name);
    }
}
