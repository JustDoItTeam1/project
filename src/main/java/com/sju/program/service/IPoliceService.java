package com.sju.program.service;

import java.util.List;
import com.sju.program.domain.Police;

/**
 * 交警人员Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IPoliceService 
{
    /**
     * 查询交警人员
     * 
     * @param policeId 交警人员ID
     * @return 交警人员
     */
    public Police selectPoliceById(Long policeId);
    /**
     * 查询交警人员
     *
     * @param username 交警人员名称
     * @return 交警人员
     */
    public Police selectUserByUserName(String username);

    public List<Police> selectAllPolice();



    /**
     * 查询交警人员列表
     * 
     * @param police 交警人员
     * @return 交警人员集合
     */
    public List<Police> selectPoliceList(Police police);

    /**
     * 新增交警人员
     * 
     * @param police 交警人员
     * @return 结果
     */
    public int insertPolice(Police police);

    /**
     * 修改交警人员
     * 
     * @param police 交警人员
     * @return 结果
     */
    public int updatePolice(Police police);

    /**
     * 批量删除交警人员
     * 
     * @param policeIds 需要删除的交警人员ID
     * @return 结果
     */
    public int deletePoliceByIds(Long[] policeIds);

    /**
     * 删除交警人员信息
     * 
     * @param policeId 交警人员ID
     * @return 结果
     */
    public int deletePoliceById(Long policeId);

    public int save(List<Police> police);
}
