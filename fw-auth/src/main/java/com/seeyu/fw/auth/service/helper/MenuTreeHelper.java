package com.seeyu.fw.auth.service.helper;


import com.seeyu.fw.auth.entity.AuthMenu;
import com.seeyu.fw.auth.entity.AuthResource;
import com.seeyu.fw.auth.vo.authority.BaseMenu;
import com.seeyu.fw.auth.vo.authority.MenuPlain;
import com.seeyu.fw.auth.vo.authority.MenuResource;
import com.seeyu.fw.auth.vo.authority.MenuTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MenuTreeHelper {

    private List<MenuResource> allMenuList;
    private List<AuthResource> userResourceList;

    public MenuTreeHelper(List<MenuResource> allMenuList, List<AuthResource> userResourceList){
        this.allMenuList = allMenuList;
        this.userResourceList = userResourceList;
    }

    public List<MenuTree> getUserMenuTrees(){
        List<MenuResource> userMenuList = getUserMenuList();

        //获取用户顶层菜单
        List<MenuResource> userTopMenuList = getTopMenuList(userMenuList, this.allMenuList);

        List<MenuTree> menuTrees = new ArrayList<>(userTopMenuList.size());
        int level = 1;
        for(MenuResource menu : userTopMenuList){
            MenuTree mt = new MenuTree();
            wrapToBaseMenu(mt, menu);
            mt.setLevel(level);
            List<MenuTree> children = genMenuTree(this.allMenuList, menu.getMenuId(), level+1);
            mt.setSubMenus(children);
            menuTrees.add(mt);
        }
        return menuTrees;
    }


    public List<MenuPlain> getUserPlainMenus(){
        List<MenuResource> userMenuList = getUserMenuList();
        //获取用户顶层菜单
        List<MenuResource> userTopMenuList = getTopMenuList(userMenuList, this.allMenuList);
        List<MenuPlain> menuTrees = new ArrayList<>(userTopMenuList.size());
        int level = 1;
        for(MenuResource menu : userTopMenuList){
            MenuPlain mt = new MenuPlain();
            wrapToBaseMenu(mt, menu);
            menuTrees.addAll(genPlainMenus(this.allMenuList, menu.getMenuId()));
            menuTrees.add(mt);
        }
        return menuTrees;
    }


    private List<MenuResource> getUserMenuList(){
        List<MenuResource> userMenuList = new LinkedList<>();
        for(MenuResource menu : this.allMenuList){
            if(menu.getMenuResourceId() == null){
                continue;
            }
            for(AuthResource resource : this.userResourceList){
                if(menu.getMenuResourceId().equals(resource.getResourceId())){
                    userMenuList.add(menu);
                }
            }
        }
        return userMenuList;
    }


    private List<MenuPlain> genPlainMenus(final List<MenuResource> allMenus, Serializable pid){
        List<MenuPlain> trees = new LinkedList<>();
        for(MenuResource menu : allMenus){
            boolean isChildFolder = menu.getMenuResourceId() == null && (pid == null && menu.getMenuPid() == null || pid != null && pid.equals(menu.getMenuPid()));
            boolean isChildFile = idIsInResourceList(menu.getMenuResourceId(), this.userResourceList) && pid.equals(menu.getMenuPid());
            if(isChildFolder || isChildFile){
                MenuPlain mt = new MenuPlain();
                wrapToBaseMenu(mt, menu);
                trees.addAll(genPlainMenus(allMenus, menu.getMenuId()));
                trees.add(mt);
            }
        }
        return trees;
    }


    private List<MenuTree> genMenuTree(final List<MenuResource> allMenus, Serializable pid, int level){
        List<MenuTree> trees = new LinkedList<>();
        for(MenuResource menu : allMenus){
            boolean isChildFolder = menu.getMenuResourceId() == null && (pid == null && menu.getMenuPid() == null || pid != null && pid.equals(menu.getMenuPid()));
            boolean isChildFile = idIsInResourceList(menu.getMenuResourceId(), this.userResourceList) && pid.equals(menu.getMenuPid());
            if(isChildFolder || isChildFile){
                MenuTree mt = new MenuTree();
                wrapToBaseMenu(mt, menu);
                mt.setLevel(level);
                //get children
                List<MenuTree> children = genMenuTree(allMenus, menu.getMenuId(), level+1);
                mt.setSubMenus(children);
                trees.add(mt);
            }
        }
        return trees;
    }


    private List<MenuResource> getTopMenuList(List<MenuResource> bottomMenuList, List<MenuResource> allMenuList){
        List<MenuResource> topMenus = new LinkedList<>();
        for(MenuResource m : bottomMenuList){
            MenuResource menu = getTopMenu(m, allMenuList);
            if(menu != null && menuIsUnique(menu, topMenus)){
                topMenus.add(menu);
            }
        }
        menusSort(topMenus);
        return topMenus;
    }


    private MenuResource getTopMenu(MenuResource menu, List<MenuResource> menuList){
        if(menu.getMenuPid() == null){
            return menu;
        }
        for(MenuResource m : menuList){
            if(menu.getMenuPid().equals(m.getMenuId())){
                return getTopMenu(m, menuList);
            }
        }
        return null;
    }


    private void menusSort(List<MenuResource> menus){
        menus.sort((o1, o2) -> {
            if (o1.getMenuOrder() == null && o2.getMenuOrder() == null) {
                return o1.getMenuText().compareTo(o2.getMenuText());
            }
            if (o1.getMenuOrder() == null) {
                return -1;
            } else if (o2.getMenuOrder() == null) {
                return 1;
            } else {
                return o1.getMenuOrder() - o2.getMenuOrder();
            }
        });

    }


    private boolean menuIsUnique(MenuResource menu, List<MenuResource> menuList){
        for(AuthMenu m : menuList){
            if(menu.getMenuId().equals(m.getMenuId())){
                return false;
            }
        }
        return true;
    }


    private boolean idIsInResourceList(Serializable resourceId, List<AuthResource> resources){
        if(resourceId == null){
            return false;
        }
        for(AuthResource re : resources){
            if(resourceId.equals(re.getResourceId())){
                return true;
            }
        }
        return false;
    }


    private void wrapToBaseMenu(BaseMenu baseMenu, MenuResource menu){
        baseMenu.setName(menu.getMenuName());
        baseMenu.setText(menu.getMenuText());
        baseMenu.setUri(menu.getResource()==null? null : menu.getResource().getResourceUri());
        baseMenu.setIcon(menu.getMenuIcon());
        baseMenu.setId(menu.getMenuId());
        baseMenu.setParentId(menu.getMenuPid());
        baseMenu.setRemark(menu.getMenuRemark());
    }

}
