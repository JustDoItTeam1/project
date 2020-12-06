package com.sju.program.mapper;

import java.util.List;
import java.util.prefs.BackingStoreException;

import com.sju.program.domain.SiegeScheme;
import com.sju.program.domain.model.BaseUser;

/**
 * 围蔽方案Mapper接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface SiegeSchemeMapper 
{
    /**
     * 查询围蔽方案
     * 
     * @param ssId 围蔽方案ID
     * @return 围蔽方案
     */
    public SiegeScheme selectSiegeSchemeById(Long ssId);

    /**
     * 查询围蔽方案列表
     * 
     * @param siegeScheme 围蔽方案
     * @return 围蔽方案集合
     */
    public List<SiegeScheme> selectSiegeSchemeList(SiegeScheme siegeScheme);


    public List<SiegeScheme> selectSiegeSchemeByBuilderId(Long BuilderId);

    public List<SiegeScheme> selectPassSiegeScheme();

    public List<SiegeScheme> selectAllSiegeSchemeList();

    public List<SiegeScheme> selectSiegeSchemeByProjectId(Long projectId);

    public int getChildNumberBySsProjectId(Long projectId);

    public String getSsBySsVerifyFlagProjectId(Long projectId);

    SiegeScheme checkSifegeSchemeUnique(SiegeScheme siegeScheme);

    int updateSiegeSchemeDeleteFlagByIds(Long[] ssId);










    /**
     * 新增围蔽方案
     * 
     * @param siegeScheme 围蔽方案
     * @return 结果
     */
    public int insertSiegeScheme(SiegeScheme siegeScheme);

    /**
     * 修改围蔽方案
     * 
     * @param siegeScheme 围蔽方案
     * @return 结果
     */
    public int updateSiegeScheme(SiegeScheme siegeScheme);

    /**
     * 删除围蔽方案
     * 
     * @param ssId 围蔽方案ID
     * @return 结果
     */
    public int deleteSiegeSchemeById(Long ssId);

    /**
     * 批量删除围蔽方案
     * 
     * @param ssIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSiegeSchemeByIds(Long[] ssIds);
}
