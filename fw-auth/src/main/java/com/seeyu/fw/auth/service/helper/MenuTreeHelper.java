//package com.seeyu.fw.auth.service.helper;
//
//
//import com.jieyundata.micro.auth.common.vo.auth.BaseMenu;
//import com.jieyundata.micro.auth.common.vo.auth.MenuPlain;
//import com.jieyundata.micro.auth.common.vo.auth.MenuTree;
//import com.jieyundata.micro.auth.server.biz.entity.Menu;
//import com.jieyundata.micro.auth.server.biz.entity.Resource;
//import com.jieyundata.micro.auth.server.biz.vo.MenuResource;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedList;
//import java.util.List;
//
//public class MenuTreeHelper {
//
//    private List<MenuResource> allMenuList;
//    private List<Resource> userResourceList;
//
//    public MenuTreeHelper(List<MenuResource> allMenuList, List<Resource> userResourceList){
//        this.allMenuList = allMenuList;
//        this.userResourceList = userResourceList;
//    }
//
//    public List<MenuTree> getUserMenuTrees(){
//        List<MenuResource> userMenuList = getUserMenuList();
//
//        //获取用户顶层菜单
//        List<MenuResource> userTopMenuList = getTopMenuList(userMenuList, this.allMenuList);
//
//        List<MenuTree> menuTrees = new ArrayList<>(userTopMenuList.size());
//        int level = 1;
//        for(MenuResource menu : userTopMenuList){
//            MenuTree mt = new MenuTree();
//            wrapToBaseMenu(mt, menu);
//            mt.setLevel(level);
//            List<MenuTree> children = genMenuTree(this.allMenuList, menu.getMeuId(), level+1);
//            mt.setSubMenus(children);
//            menuTrees.add(mt);
//        }
//        return menuTrees;
//    }
//
//
//    public List<MenuPlain> getUserPlainMenus(){
//        List<MenuResource> userMenuList = getUserMenuList();
//        //获取用户顶层菜单
//        List<MenuResource> userTopMenuList = getTopMenuList(userMenuList, this.allMenuList);
//        List<MenuPlain> menuTrees = new ArrayList<>(userTopMenuList.size());
//        int level = 1;
//        for(MenuResource menu : userTopMenuList){
//            MenuPlain mt = new MenuPlain();
//            wrapToBaseMenu(mt, menu);
//            menuTrees.addAll(genPlainMenus(this.allMenuList, menu.getMeuId()));
//            menuTrees.add(mt);
//        }
//        return menuTrees;
//    }
//
//
//    private List<MenuResource> getUserMenuList(){
//        List<MenuResource> userMenuList = new LinkedList<>();
//        for(MenuResource menu : this.allMenuList){
//            if(menu.getMeuReeid() == null){
//                continue;
//            }
//            for(Resource resource : this.userResourceList){
//                if(menu.getMeuReeid().equals(resource.getReeId())){
//                    userMenuList.add(menu);
//                }
//            }
//        }
//        return userMenuList;
//    }
//
//
//    private List<MenuPlain> genPlainMenus(final List<MenuResource> allMenus, Serializable pid){
//        List<MenuPlain> trees = new LinkedList<>();
//        for(MenuResource menu : allMenus){
//            boolean isChildFolder = menu.getMeuReeid() == null && (pid == null && menu.getMeuPid() == null || pid != null && pid.equals(menu.getMeuPid()));
//            boolean isChildFile = idIsInResourceList(menu.getMeuReeid(), this.userResourceList) && pid.equals(menu.getMeuPid());
//            if(isChildFolder || isChildFile){
//                MenuPlain mt = new MenuPlain();
//                wrapToBaseMenu(mt, menu);
//                trees.addAll(genPlainMenus(allMenus, menu.getMeuId()));
//                trees.add(mt);
//            }
//        }
//        return trees;
//    }
//
//
//    private List<MenuTree> genMenuTree(final List<MenuResource> allMenus, Serializable pid, int level){
//        List<MenuTree> trees = new LinkedList<>();
//        for(MenuResource menu : allMenus){
//            boolean isChildFolder = menu.getMeuReeid() == null && (pid == null && menu.getMeuPid() == null || pid != null && pid.equals(menu.getMeuPid()));
//            boolean isChildFile = idIsInResourceList(menu.getMeuReeid(), this.userResourceList) && pid.equals(menu.getMeuPid());
//            if(isChildFolder || isChildFile){
//                MenuTree mt = new MenuTree();
//                wrapToBaseMenu(mt, menu);
//                mt.setLevel(level);
//                //get children
//                List<MenuTree> children = genMenuTree(allMenus, menu.getMeuId(), level+1);
//                mt.setSubMenus(children);
//                trees.add(mt);
//            }
//        }
//        return trees;
//    }
//
//
//    private List<MenuResource> getTopMenuList(List<MenuResource> bottomMenuList, List<MenuResource> allMenuList){
//        List<MenuResource> topMenus = new LinkedList<>();
//        for(MenuResource m : bottomMenuList){
//            MenuResource menu = getTopMenu(m, allMenuList);
//            if(menu != null && menuIsUnique(menu, topMenus)){
//                topMenus.add(menu);
//            }
//        }
//        menusSort(topMenus);
//        return topMenus;
//    }
//
//
//    private MenuResource getTopMenu(MenuResource menu, List<MenuResource> menuList){
//        if(menu.getMeuPid() == null){
//            return menu;
//        }
//        for(MenuResource m : menuList){
//            if(menu.getMeuPid().equals(m.getMeuId())){
//                return getTopMenu(m, menuList);
//            }
//        }
//        return null;
//    }
//
//
//    private void menusSort(List<MenuResource> menus){
//        Collections.sort(menus, (o1, o2) -> {
//            if(o1.getMeuOrder() == null && o2.getMeuOrder() == null){
//                return o1.getMeuText().compareTo(o2.getMeuText());
//            }
//            if(o1.getMeuOrder() == null){
//                return -1;
//            }
//            else if(o2.getMeuOrder() == null){
//                return 1;
//            }
//            else{
//                return o1.getMeuOrder() - o2.getMeuOrder();
//            }
//        });
//
//    }
//
//
//    private boolean menuIsUnique(MenuResource menu, List<MenuResource> menuList){
//        for(Menu m : menuList){
//            if(menu.getMeuId().equals(m.getMeuId())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    private boolean idIsInResourceList(Serializable resourceId, List<Resource> resources){
//        if(resourceId == null){
//            return false;
//        }
//        for(Resource re : resources){
//            if(resourceId.equals(re.getReeId())){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    private void wrapToBaseMenu(BaseMenu baseMenu, MenuResource menu){
//        baseMenu.setName(menu.getMeuName());
//        baseMenu.setText(menu.getMeuText());
//        baseMenu.setUri(menu.getReeUri());
//        baseMenu.setIcon(menu.getMeuIcon());
//        baseMenu.setId(menu.getMeuId());
//        baseMenu.setParentId(menu.getMeuPid());
//        baseMenu.setRemark(menu.getMeuRemark());
//    }
//
//}
