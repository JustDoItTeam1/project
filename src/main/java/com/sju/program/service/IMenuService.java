package com.sju.program.service;

import java.util.List;
import java.util.Set;

import com.sju.program.domain.Menu;
import com.sju.program.domain.vo.RouterVo;

/**
 * 菜单Service接口
 * 
 * @author JustDoItTeam
 * @date 2020-11-03
 */
public interface IMenuService 
{
    /**
     * 查询菜单
     * 
     * @param menuId 菜单ID
     * @return 菜单
     */
    public Menu selectMenuById(Long menuId);

    /**
     * 查询菜单列表
     * 
     * @param menu 菜单
     * @return 菜单集合
     */
    public List<Menu> selectMenuList(Menu menu);

    /**
     * 查询菜单列表
     *
     *
     * @return 菜单集合
     */
    public List<Menu> selectAllMenu();

    public Set<String> selectMenuPermsByAuthenticate(int auth);

    public List<Menu> selectMenusByAuthenticate(int auth);

    public List<Menu> getChildPerms(List<Menu> list, int parentId);


    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<Menu> menus);
    /**
     * 新增菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    public int insertMenu(Menu menu);

    /**
     * 修改菜单
     * 
     * @param menu 菜单
     * @return 结果
     */
    public int updateMenu(Menu menu);

    /**
     * 批量删除菜单
     * 
     * @param menuIds 需要删除的菜单ID
     * @return 结果
     */
    public int deleteMenuByIds(Long[] menuIds);

    /**
     * 删除菜单信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);
}
