package com.sju.program.service.impl;

import java.util.List;

import com.sju.program.domain.model.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.SiegeSchemeMapper;
import com.sju.program.domain.SiegeScheme;
import com.sju.program.service.ISiegeSchemeService;

/**
 * 围蔽方案Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class SiegeSchemeServiceImpl implements ISiegeSchemeService 
{
    @Autowired
    private SiegeSchemeMapper siegeSchemeMapper;

    /**
     * 查询围蔽方案
     * 
     * @param ssId 围蔽方案ID
     * @return 围蔽方案
     */
    @Override
    public SiegeScheme selectSiegeSchemeById(Long ssId)
    {
        return siegeSchemeMapper.selectSiegeSchemeById(ssId);
    }

    /**
     * 查询围蔽方案列表
     * 
     * @param baseUser 用户基类
     * @return 围蔽方案
     */
    @Override
    public List<SiegeScheme> selectSiegeSchemeList(BaseUser baseUser)
    {
        //return siegeSchemeMapper.selectSiegeSchemeList(baseUser);
        return null;
    }

    @Override
    public List<SiegeScheme> selectSiegeSchemeByBuilderId(Long builderId) {
        return siegeSchemeMapper.selectSiegeSchemeByBuilderId(builderId);
    }

    @Override
    public List<SiegeScheme> selectAllSiegeSchemeList() {
        return siegeSchemeMapper.selectAllSiegeSchemeList();
    }

    @Override
    public List<SiegeScheme> selectPassSiegeScheme() {
        return siegeSchemeMapper.selectPassSiegeScheme();
    }

    /**
     * 新增围蔽方案
     * 
     * @param siegeScheme 围蔽方案
     * @return 结果
     */
    @Override
    public int insertSiegeScheme(SiegeScheme siegeScheme)
    {
        return siegeSchemeMapper.insertSiegeScheme(siegeScheme);
    }

    /**
     * 修改围蔽方案
     * 
     * @param siegeScheme 围蔽方案
     * @return 结果
     */
    @Override
    public int updateSiegeScheme(SiegeScheme siegeScheme)
    {
        return siegeSchemeMapper.updateSiegeScheme(siegeScheme);
    }

    /**
     * 批量删除围蔽方案
     * 
     * @param ssIds 需要删除的围蔽方案ID
     * @return 结果
     */
    @Override
    public int deleteSiegeSchemeByIds(Long[] ssIds)
    {
        return siegeSchemeMapper.deleteSiegeSchemeByIds(ssIds);
    }

    /**
     * 删除围蔽方案信息
     * 
     * @param ssId 围蔽方案ID
     * @return 结果
     */
    @Override
    public int deleteSiegeSchemeById(Long ssId)
    {
        return siegeSchemeMapper.deleteSiegeSchemeById(ssId);
    }
}
