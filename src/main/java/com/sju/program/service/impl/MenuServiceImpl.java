package com.sju.program.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sju.program.mapper.MenuMapper;
import com.sju.program.domain.Menu;
import com.sju.program.service.IMenuService;

/**
 * 菜单Service业务层处理
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
@Service
public class MenuServiceImpl implements IMenuService 
{
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询菜单
     * 
     * @param menuId 菜单ID
     * @return 菜单
     */
    @Override
    public Menu selectMenuById(Long menuId)
    {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 查询菜单列表
     * 
     * @param menu 菜单
     * @return 菜单
     */
    @Override
    public List<Menu> selectMenuList(Menu menu)
    {
        return menuMapper.selectMenuList(menu);
    }

    /**
     * 新增菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu)
    {
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu)
    {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 批量删除菜单
     * 
     * @param menuIds 需要删除的菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuByIds(Long[] menuIds)
    {
        return menuMapper.deleteMenuByIds(menuIds);
    }

    /**
     * 删除菜单信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId)
    {
        return menuMapper.deleteMenuById(menuId);
    }
}
