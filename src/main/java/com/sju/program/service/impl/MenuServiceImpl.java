package com.sju.program.service.impl;

import java.util.*;

import com.sju.program.constant.UserConstants;
import com.sju.program.domain.vo.MetaVo;
import com.sju.program.domain.vo.RouterVo;
import com.sju.program.utils.StringUtils;
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

    @Override
    public List<Menu> selectAllMenu() {
        return menuMapper.selectAllMenu();
    }

    @Override
    public Set<String> selectMenuPermsByAuthenticate(int auth) {
        return menuMapper.selectMenuPermsByAuthenticate(auth);
    }

    @Override
    public List<Menu> selectMenusByAuthenticate(int auth) {
        return menuMapper.selectMenusByAuthenticate(auth);
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


    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<Menu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (Menu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setHidden(false);
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getMenuIcon()));
            List<Menu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            else if (isMeunFrame(menu))
            {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getMenuPath());
                children.setComponent(menu.getMenuComponent());
                children.setName(StringUtils.capitalize(menu.getMenuPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getMenuIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    @Override
    public List<Menu> getChildPerms(List<Menu> list, int parentId)
    {
        List<Menu> returnList = new ArrayList<Menu>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();)
        {
            Menu t = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getMenuParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<Menu> list, Menu t)
    {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<Menu> it = childList.iterator();
                while (it.hasNext())
                {
                    Menu n = (Menu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }


    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> list, Menu t)
    {
        List<Menu> tlist = new ArrayList<Menu>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext())
        {
            Menu n = (Menu) it.next();
            if (n.getMenuParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(Menu menu)
    {
        String routerName = StringUtils.capitalize(menu.getMenuPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }


    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu)
    {
        String routerPath = menu.getMenuPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getMenuParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
        {
            routerPath = "/" + menu.getMenuPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(Menu menu)
    {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getMenuComponent()) && !isMeunFrame(menu))
        {
            component = menu.getMenuComponent();
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(Menu menu)
    {
        return menu.getMenuParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType());
    }
}
