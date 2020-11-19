package com.sju.program.service.impl;

import java.util.*;

import com.sju.program.constant.UserConstants;
import com.sju.program.domain.Project;
import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.vo.SieheSchemeParentVo;
import com.sju.program.mapper.BuilderMapper;
import com.sju.program.mapper.ProjectMapper;
import com.sju.program.utils.StringUtils;
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

    @Autowired
    private BuilderMapper builderMapper;

    @Autowired
    private ProjectMapper projectMapper;

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

    @Override
    public List<SieheSchemeParentVo> selectSiegeSchemeBySearch(List<SieheSchemeParentVo> list, String projectInfo) {
        List<SieheSchemeParentVo> sieheSchemeParentVoList=new LinkedList<>();
        for(SieheSchemeParentVo sieheSchemeParentVo:list){
            if(sieheSchemeParentVo.getSsBuilderName().contains(projectInfo)||sieheSchemeParentVo.getSsProjectName().contains(projectInfo)){
                sieheSchemeParentVoList.add(sieheSchemeParentVo);
            }
        }
        return sieheSchemeParentVoList;
    }

    @Override
    public String checkSifegeSchemeUnique(SiegeScheme sieheScheme) {
            SiegeScheme siegeScheme=siegeSchemeMapper.checkSifegeSchemeUnique(sieheScheme);
            if(StringUtils.isNotNull(siegeScheme)){
                return UserConstants.NOT_UNIQUE;
            }
            return UserConstants.UNIQUE;
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
        return siegeSchemeMapper.updateSiegeSchemeDeleteFlagByIds(ssIds);
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

    @Override
    public List<SieheSchemeParentVo> buildSiegeScheme(List<SiegeScheme> siegeSchemes){
        Map<String,String> map=new HashMap<String, String>();
        //List<Map<Long,List<SiegeScheme>>> mapList=new ArrayList<>();
        Set<Long> set=new LinkedHashSet<>();
        Long i=0L;
        for(SiegeScheme siegeScheme:siegeSchemes){
            set.add(siegeScheme.getSsProjectId());
            i=siegeScheme.getSsId();
        }

        List<SieheSchemeParentVo> list=new ArrayList<>();
        for(Long projectId:set){
            SieheSchemeParentVo sieheSchemeParentVo=new SieheSchemeParentVo();
//            Map<String,String> map1=new HashMap<String, String>();
//            Map<String,List<SiegeScheme>> map2=new HashMap<>();
//            Map<String,Object> map3=new HashMap<>();
            sieheSchemeParentVo.setChild(siegeSchemeMapper.selectSiegeSchemeByProjectId(projectId));
            sieheSchemeParentVo.setSsId(++i);
            sieheSchemeParentVo.setSsVerifyFlag(siegeSchemeMapper.getSsBySsVerifyFlagProjectId(projectId));
            sieheSchemeParentVo.setSs(true);
            sieheSchemeParentVo.setSsmap(false);
            sieheSchemeParentVo.setChildrennum(siegeSchemeMapper.getChildNumberBySsProjectId(projectId));
            sieheSchemeParentVo.setSsBuilderName(projectMapper.selectBuilderNameByprojectId(projectId));
            sieheSchemeParentVo.setSsProjectId(projectId);
            sieheSchemeParentVo.setSsProjectName(projectMapper.selectProjectNameById(projectId));

            list.add(sieheSchemeParentVo);
        }
//           if(map.containsKey(siegeScheme.getSsProjectId())){
//               List<SiegeScheme> list=map.get(siegeScheme.getSsProjectId());
//               siegeScheme.setSs(true);
//               siegeScheme.setSsmap(false);
//               list.add(siegeScheme);
//               map.put(siegeScheme.getSsProjectId(),list);
//           }else {
//
//               Map<String,String> map2=new HashMap<String, String>();
//               map1.put("ss","true");
//               map1.put("ssmap","true");
//               map1.put("childrennum","false");
//               map1.put("ssBuilderName",String.valueOf(builderMapper.selectBuilderNameById(siegeScheme.getSsBuilderId())));
//               map1.put("ssProjectId",String.valueOf(siegeScheme.getSsProjectId()));
//               map1.put("ssProjectName",String.valueOf(siegeScheme.getSsProjectId()));
//               map1.put("childrennum",String.valueOf(siegeSchemeMapper.getChildNumberBySsProjectId(siegeScheme.getSsProjectId())));
//               List<SiegeScheme> list=new LinkedList<>();
//               list.add(siegeScheme);
//               map.put(siegeScheme.getSsProjectId(),list);
//               map2.putAll(map,map1);
//           }
//
//        }
        return list;
    }
}

