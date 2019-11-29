//package com.seeyu.fw.auth.service;
//
//import com.jieyundata.micro.auth.common.to.MenuTo;
//import com.jieyundata.micro.auth.server.biz.entity.Menu;
//import com.jieyundata.micro.auth.server.biz.mapper.MenuMapper;
//import com.jieyundata.micro.auth.server.biz.vo.MenuResource;
//import com.seeyu.normal.utils.BeanUtils;
//import com.seeyu.normal.utils.GridData;
//import com.seeyu.normal.utils.Page;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author seeyu
// * @date 2019/4/29
// */
//@Service
//public class AuthMenuService {
//
//    @Autowired
//    private AuthResourceService resourceService;
//    @Autowired
//    private MenuMapper menuMapper;
//
//    public GridData grid(MenuTo menuTo, Page page){
//        MenuResource menu = cast2MenuResource(menuTo);
//        page.setDefaultOrderBy("meuAddtime desc").startPage();
//        return GridData.create((com.github.pagehelper.Page)menuMapper.getMenuResources(menu));
//    }
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteMenu(Integer meuId){
//        Menu menu = this.menuMapper.selectByPrimaryKey(meuId);
//        if(menu != null){
//            if(menu.getMeuReeid() != null){
//                this.resourceService.deleteResource(menu.getMeuReeid());
//            }
//            this.deleteSubMenu(menu.getMeuId());
//            this.menuMapper.deleteByPrimaryKey(menu.getMeuId());
//        }
//    }
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteSubMenu(Integer pid){
//        for(Menu menu : this.selectMenusByPid(pid)){
//            this.deleteMenu(menu.getMeuId());
//        }
//    }
//
//
//    private MenuResource cast2MenuResource(MenuTo menuTo){
//        MenuResource menuResource = new MenuResource();
//        BeanUtils.wrap(menuTo, menuResource);
//        return menuResource;
//    }
//
//
//    private List<Menu> selectMenusByPid(Integer pid){
//        if(pid == null){
//            return new ArrayList<>(0);
//        }
//        else{
//            Menu menu = new Menu();
//            menu.setMeuPid(pid);
//            return this.menuMapper.getMenus(menu);
//        }
//    }
//
//}
