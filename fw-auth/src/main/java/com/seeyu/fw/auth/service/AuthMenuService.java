package com.seeyu.fw.auth.service;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.fw.auth.mapper.AuthMenuMapper;
import com.seeyu.fw.auth.vo.authority.MenuResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author seeyu
 * @date 2019/4/29
 */
@Service
public class AuthMenuService {

    @Autowired
    private AuthMenuMapper menuMapper;

    public List<MenuResource> getServiceActiveMenus(Integer serviceId){
        return this.menuMapper.selectActiveMenuResourceListByServiceId(serviceId);
    }

//    public GridData grid(MenuTo menuTo, Page page){
//        MenuResource menu = cast2MenuResource(menuTo);
//        page.setDefaultOrderBy("meuAddtime desc").startPage();
//        return GridData.create((com.github.pagehelper.Page)menuMapper.getMenuResources(menu));
//    }


  //  @Transactional(rollbackFor = Exception.class)
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


//    @Transactional(rollbackFor = Exception.class)
//    public void deleteSubMenu(Integer pid){
//        for(Menu menu : this.selectMenusByPid(pid)){
//            this.deleteMenu(menu.getMeuId());
//        }
//    }


//    private MenuResource cast2MenuResource(MenuTo menuTo){
//        MenuResource menuResource = new MenuResource();
//        BeanUtils.wrap(menuTo, menuResource);
//        return menuResource;
//    }


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

}
