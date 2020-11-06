package com.sju.program.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.PoliceMapper;
import com.sju.program.domain.Police;
import com.sju.program.service.IPoliceService;

/**
 * 交警人员Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class PoliceServiceImpl implements IPoliceService 
{
    @Autowired
    private PoliceMapper policeMapper;

    /**
     * 查询交警人员
     * 
     * @param policeId 交警人员ID
     * @return 交警人员
     */
    @Override
    public Police selectPoliceById(Long policeId)
    {
        return policeMapper.selectPoliceById(policeId);
    }

    @Override
    public Police selectUserByUserName(String username) {
        return policeMapper.selectUserByUserName(username);
    }

    /**
     * 查询交警人员列表
     * 
     * @param police 交警人员
     * @return 交警人员
     */
    @Override
    public List<Police> selectPoliceList(Police police)
    {
        return policeMapper.selectPoliceList(police);
    }

    /**
     * 新增交警人员
     * 
     * @param police 交警人员
     * @return 结果
     */
    @Override
    public int insertPolice(Police police)
    {
        return policeMapper.insertPolice(police);
    }

    /**
     * 修改交警人员
     * 
     * @param police 交警人员
     * @return 结果
     */
    @Override
    public int updatePolice(Police police)
    {
        return policeMapper.updatePolice(police);
    }

    /**
     * 批量删除交警人员
     * 
     * @param policeIds 需要删除的交警人员ID
     * @return 结果
     */
    @Override
    public int deletePoliceByIds(Long[] policeIds)
    {
        return policeMapper.deletePoliceByIds(policeIds);
    }

    /**
     * 删除交警人员信息
     * 
     * @param policeId 交警人员ID
     * @return 结果
     */
    @Override
    public int deletePoliceById(Long policeId)
    {
        return policeMapper.deletePoliceById(policeId);
    }
}
