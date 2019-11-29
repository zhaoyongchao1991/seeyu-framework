//package com.seeyu.fw.auth.service.helper;
//
//import com.jieyundata.micro.auth.common.vo.ServiceAuthority;
//import com.jieyundata.micro.auth.common.vo.auth.MenuRegister;
//import com.jieyundata.micro.auth.common.vo.auth.ResourceRegister;
//import com.jieyundata.micro.auth.common.vo.auth.ServiceRegister;
//import com.jieyundata.micro.auth.server.biz.entity.Menu;
//import com.jieyundata.micro.auth.server.biz.entity.MenuResource;
//import com.jieyundata.micro.auth.server.biz.entity.Resource;
//import com.jieyundata.micro.auth.server.biz.entity.Service;
//import com.jieyundata.micro.auth.server.biz.mapper.MenuMapper;
//import com.jieyundata.micro.auth.server.biz.mapper.MenuResourceMapper;
//import com.jieyundata.micro.auth.server.biz.mapper.ResourceMapper;
//import com.jieyundata.micro.auth.server.biz.mapper.ServiceMapper;
//import com.jieyundata.micro.auth.server.biz.service.MenuService;
//import com.jieyundata.micro.auth.server.biz.service.ResourceService;
//import com.jieyundata.micro.auth.server.context.ServiceInfoContext;
//import com.seeyu.core.constant.ActivationState;
//import com.seeyu.normal.utils.BeanUtils;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author seeyu
// * @date 2019/8/6
// */
//@Component
//public class AuthorityServiceRegisterSaveHelper {
//
//    @Autowired
//    private MenuService menuService;
//    @Autowired
//    private ResourceService resourceService;
//    @Autowired
//    private ServiceMapper serviceMapper;
//    @Autowired
//    private MenuMapper menuMapper;
//    @Autowired
//    private ResourceMapper resourceMapper;
//    @Autowired
//    private MenuResourceMapper menuResourceMapper;
//
//
//    Service saveServiceAuthority(ServiceAuthority serviceAuthority){
//        Service service =  this.saveOrUpdateService(serviceAuthority, null);
//        this.saveOrUpdateMenus(serviceAuthority.getMenus(), service.getSeeId(), null);
//        this.saveOrUpdateResources(serviceAuthority.getResources(), service.getSeeId());
//        return service;
//    }
//
//    void updateServiceAuthority(ServiceAuthority serviceAuthority, Service service){
//        service = this.saveOrUpdateService(serviceAuthority, service);
//        this.deleteResources(serviceAuthority);
//        this.deleteMenus(serviceAuthority);
//        this.saveOrUpdateMenus(serviceAuthority.getMenus(), service.getSeeId(), null);
//        this.saveOrUpdateResources(serviceAuthority.getResources(), service.getSeeId());
//    }
//
//    private Service saveOrUpdateService(ServiceAuthority serviceAuthority, Service service){
//        if(service == null){
//            service = this.wrapService(serviceAuthority);
//            this.serviceMapper.insert(service);
//        }
//        else{
//            service.setSeeName(serviceAuthority.getSeeName());
//            service.setSeeText(serviceAuthority.getSeeText());
//            service.setSeeSystem(serviceAuthority.getSeeSystem());
//            service.setSeeInfo(serviceAuthority.getSeeInfo());
//            this.serviceMapper.updateByPrimaryKey(service);
//        }
//        return service;
//    }
//
//    private void deleteMenus(ServiceAuthority serviceAuthority){
//        Map<String, ServiceMenuWrap> menuRegisterWrapMap = this.getCurrentMenusWrapMap();
//        this.excludeExistMenu(menuRegisterWrapMap, serviceAuthority.getMenus());
//        for (Map.Entry<String, ServiceMenuWrap> entry : menuRegisterWrapMap.entrySet() ){
//            if (entry.getValue().isDel()){
//                this.menuService.deleteMenu(entry.getValue().getMeuId());
//            }
//        }
//    }
//
//    private void deleteResources(ServiceAuthority serviceAuthority){
//        Map<String, ServiceResourceWrap> resourceWrapMap = this.getCurrentResourceWrapMap();
//        this.excludeExistResource(resourceWrapMap, serviceAuthority.getResources());
//        this.excludeExistResource2(resourceWrapMap, serviceAuthority.getMenus());
//        for (Map.Entry<String, ServiceResourceWrap> entry : resourceWrapMap.entrySet() ){
//            if (entry.getValue().isDel()){
//                this.resourceService.deleteResource(entry.getValue().getReeId());
//            }
//        }
//    }
//
//    private void excludeExistMenu(Map<String, ServiceMenuWrap> menuRegisterWrapMap, List<MenuRegister> menuRegisterList){
//        if(menuRegisterList != null){
//            for(MenuRegister menuRegister :  menuRegisterList){
//                ServiceMenuWrap wrap = menuRegisterWrapMap.get(menuRegister.getMeuName());
//                if(wrap != null){
//                    wrap.setDel(false);
//                }
//                this.excludeExistMenu(menuRegisterWrapMap, menuRegister.getSubMenus());
//            }
//        }
//    }
//
//    private void excludeExistResource2(Map<String, ServiceResourceWrap> resourceWrapMap, List<MenuRegister> menuRegisterList){
//        if(menuRegisterList != null){
//            for(MenuRegister menuRegister :  menuRegisterList){
//                if(menuRegister.getRefResource() != null){
//                    ServiceResourceWrap wrap = resourceWrapMap.get(menuRegister.getRefResource().getReeName());
//                    if(wrap != null){
//                        wrap.setDel(false);
//                    }
//                }
//                this.excludeExistResource2(resourceWrapMap, menuRegister.getSubMenus());
//            }
//        }
//    }
//
//    private void excludeExistResource(Map<String, ServiceResourceWrap> resourceWrapMap, List<ResourceRegister> resourceRegisterList){
//        if(resourceRegisterList != null){
//            for(ResourceRegister resourceRegister :  resourceRegisterList){
//                ServiceResourceWrap wrap = resourceWrapMap.get(resourceRegister.getReeName());
//                if(wrap != null){
//                    wrap.setDel(false);
//                }
//            }
//        }
//    }
//
//    private Map<String, ServiceMenuWrap> getCurrentMenusWrapMap(){
//        Map<String, ServiceMenuWrap> meus = new HashMap<>();
//        for (Menu m : this.getCurrentServiceMenus()){
//            ServiceMenuWrap wrap = new ServiceMenuWrap(m);
//            wrap.setDel(true);
//            meus.put(m.getMeuName(), wrap);
//        }
//        return  meus;
//    }
//
//    private Map<String, ServiceResourceWrap>  getCurrentResourceWrapMap(){
//        Map<String, ServiceResourceWrap> resourceWrapMap = new HashMap<>();
//        for (Resource m : this.getCurrentServiceResources()){
//            ServiceResourceWrap wrap = new ServiceResourceWrap(m);
//            wrap.setDel(true);
//            resourceWrapMap.put(m.getReeName(), wrap);
//        }
//        return resourceWrapMap;
//    }
//
//    private List<Menu> getCurrentServiceMenus(){
//        Menu menu = new Menu();
//        menu.setMeuSeeid(ServiceInfoContext.get().getSeeId());
//        return this.menuMapper.getMenus(menu);
//    }
//
//    private List<Resource> getCurrentServiceResources(){
//        Resource resource = new Resource();
//        resource.setReeSeeid(ServiceInfoContext.get().getSeeId());
//        return this.resourceMapper.getResources(resource);
//    }
//
//    private void saveOrUpdateMenus(List<MenuRegister> menus, Integer serviceId, Integer pid){
//        if(menus != null){
//            for(int i = 0; i < menus.size(); i++){
//                MenuRegister menuRegister = menus.get(i);
//                Menu menu = this.saveOrUpdateMenu(menuRegister, serviceId, pid, (i+1));
//                this.saveMenuResources(serviceId, menu, menuRegister.getHasResources());
//                if(menuRegister.getSubMenus() != null){
//                    this.saveOrUpdateMenus(menuRegister.getSubMenus(), serviceId, menu.getMeuId());
//                }
//            }
//        }
//    }
//
//    private Menu saveOrUpdateMenu(MenuRegister menuRegister, Integer serviceId, Integer pid, int order){
//        Resource resource = null;
//        if(menuRegister.getRefResource() != null){
//            resource = this.saveOrUpdateResource(menuRegister.getRefResource(), serviceId);
//        }
//
//        Menu menu = this.getMenu(serviceId, menuRegister.getMeuName());
//        if(menu == null){
//            menu = wrapMenu(menuRegister, serviceId, pid, resource, order);
//            this.menuMapper.insert(menu);
//        }
//        else{
//            menu.setMeuSeeid(serviceId);
//            menu.setMeuPid(pid);
//            menu.setMeuName(menuRegister.getMeuName());
//            menu.setMeuText(menuRegister.getMeuText());
//            menu.setMeuIcon(menuRegister.getMeuIcon());
//            menu.setMeuRemark(menuRegister.getMeuRemark());
//            menu.setMeuOrder(order);
//            menu.setMeuReeid(resource == null ? null : resource.getReeId());
//            this.menuMapper.updateByPrimaryKey(menu);
//        }
//        return menu;
//    }
//
//    private void saveOrUpdateResources(List<ResourceRegister> resources, Integer serviceId){
//        if(resources != null){
//            for(ResourceRegister resourceRegister : resources){
//                this.saveOrUpdateResource(resourceRegister, serviceId);
//            }
//        }
//    }
//
//    private void saveMenuResources(Integer serviceId, Menu menu, List<ResourceRegister> hasResources){
//        if(hasResources == null || hasResources.size() == 0){
//            return;
//        }
//        this.clearMenuResources(menu);
//        for(ResourceRegister resourceRegister : hasResources){
//            Resource resource = this.saveOrUpdateResource(resourceRegister, serviceId);
//            this.saveMenuResource(menu, resource);
//        }
//    }
//
//    private void clearMenuResources(Menu menu){
//        this.menuResourceMapper.clearMenuResource(menu.getMeuId());
//    }
//
//    private void saveMenuResource(Menu menu, Resource resource){
//        MenuResource mr = new MenuResource();
//        mr.setMreMenuId(menu.getMeuId());
//        mr.setMreResourceId(resource.getReeId());
//        mr.setMreAddTime(new Date());
//        this.menuResourceMapper.insert(mr);
//    }
//
//    private Resource saveOrUpdateResource(ResourceRegister resourceRegister, Integer serviceId){
//        Resource resource = getResource(serviceId, resourceRegister.getReeName());
//        if(resource == null){
//            resource = wrapResource(resourceRegister, serviceId);
//            this.resourceMapper.insert(resource);
//        }
//        else{
//            resource.setReeName(resourceRegister.getReeName());
//            resource.setReeUri(resourceRegister.getReeUri());
//            resource.setReeLevel(resourceRegister.getReeLevel().getValue());
//            this.resourceMapper.updateByPrimaryKey(resource);
//        }
//        return resource;
//    }
//
//    private Resource getResource(Integer reeSeeid, String reeName){
//        return this.resourceMapper.selectResourceByServiceAndName(reeSeeid, reeName);
//    }
//
//    private Menu getMenu(Integer meuSeeid, String meuName){
//        return this.menuMapper.selectMenuByServiceAndName(meuSeeid, meuName);
//    }
//
//
//    private Menu wrapMenu(MenuRegister menuRegister, Integer serviceId, Integer pid, Resource refResource, int order){
//        Menu menu = new Menu();
//        menu.setMeuSeeid(serviceId);
//        menu.setMeuName(menuRegister.getMeuName());
//        menu.setMeuText(menuRegister.getMeuText());
//        menu.setMeuIcon(menuRegister.getMeuIcon());
//        menu.setMeuOrder(order);
//        menu.setMeuRemark(menuRegister.getMeuRemark());
//        menu.setMeuReeid(refResource == null ? null : refResource.getReeId());
//        menu.setMeuUri(refResource == null ? null : refResource.getReeUri());
//        menu.setMeuPid(pid);
//        menu.setMeuActive(ActivationState.getActiveState());
//        menu.setMeuAddtime(new Date());
//        menu.setMeuModifytime(menu.getMeuAddtime());
//        return menu;
//    }
//
//    private Resource wrapResource(ResourceRegister resourceRegister, Integer serviceId){
//        Resource resource = new Resource();
//        resource.setReeSeeid(serviceId);
//        resource.setReeName(resourceRegister.getReeName());
//        resource.setReeUri(resourceRegister.getReeUri());
//        resource.setReeLevel(resourceRegister.getReeLevel().getValue());
//        resource.setReeActive(ActivationState.getActiveState());
//        resource.setReeAddtime(new Date());
//        resource.setReeModifytime(resource.getReeAddtime());
//        return resource;
//    }
//
//    private Service wrapService(ServiceRegister serviceRegister){
//        Service service = new Service();
//        service.setSeeName(serviceRegister.getSeeName());
//        service.setSeeSystem(serviceRegister.getSeeSystem());
//        service.setSeeText(serviceRegister.getSeeText());
//        service.setSeeInfo(serviceRegister.getSeeInfo());
//        service.setSeeActive(ActivationState.getActiveState());
//        service.setSeeAddTime(new Date());
//        service.setSeeModifyTime(service.getSeeAddTime());
//        return service;
//    }
//
//    @Data
//    class ServiceMenuWrap extends Menu {
//        private boolean isDel;
//        ServiceMenuWrap(Menu menu){
//            BeanUtils.wrap(menu, this);
//        }
//    }
//
//    @Data
//    class ServiceResourceWrap extends Resource{
//        private boolean isDel;
//        ServiceResourceWrap(Resource resource){
//            BeanUtils.wrap(resource, this);
//        }
//    }
//
//}
