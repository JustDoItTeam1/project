package com.sju.program.service;

import java.util.List;
import java.util.Map;

import com.sju.program.domain.Project;
import com.sju.program.domain.SiegeScheme;
import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.vo.SieheSchemeParentVo;

/**
 * 围蔽方案Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface ISiegeSchemeService 
{
    /**
     * 查询围蔽方案
     * 
     * @param ssId 围蔽方案ID
     * @return 围蔽方案
     */
    public SiegeScheme selectSiegeSchemeById(Long ssId);


    public List<SiegeScheme> selectSiegeSchemeByBuilderId(Long ssId);


    public List<SiegeScheme> selectAllSiegeSchemeList();


    public List<SiegeScheme> selectPassSiegeScheme();

    public List<SieheSchemeParentVo> buildSiegeScheme(List<SiegeScheme> list);

    List<SieheSchemeParentVo> selectSiegeSchemeBySearch(List<SieheSchemeParentVo> list, String projectInfo);

    String checkSifegeSchemeUnique(SiegeScheme sieheScheme);



    /**
     * 查询围蔽方案列表
     * 
     * @param siegeScheme 围蔽方案
     * @return 围蔽方案集合
     */
    public List<SiegeScheme> selectSiegeSchemeList(BaseUser baseUser);

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
     * 批量删除围蔽方案
     * 
     * @param ssIds 需要删除的围蔽方案ID
     * @return 结果
     */
    public int deleteSiegeSchemeByIds(Long[] ssIds);

    /**
     * 删除围蔽方案信息
     * 
     * @param ssId 围蔽方案ID
     * @return 结果
     */
    public int deleteSiegeSchemeById(Long ssId);
}
