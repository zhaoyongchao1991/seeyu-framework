//package com.seeyu.fw.auth.service;
//
//import com.jieyundata.micro.auth.server.biz.entity.RoleResource;
//import com.jieyundata.micro.auth.server.biz.mapper.RoleResourceMapper;
//import com.jieyundata.micro.auth.server.constant.message.ResourceMessageConstant;
//import com.jieyundata.micro.auth.server.constant.message.RoleMessageConstant;
//import com.seeyu.normal.utils.FormValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//
///**
// * @author seeyu
// * @date 2019/4/29
// */
//@Service
//public class AuthRoleResourceService {
//
//    @Autowired
//    private RoleResourceMapper roleResourceMapper;
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public void addRoleResource(RoleResource roleResource){
//        FormValidator.required(RoleMessageConstant.TITLE_ROLE_ID, roleResource.getRorRoeid());
//        FormValidator.required(ResourceMessageConstant.TITLE_RESOURCE, roleResource.getRorReeid());
//        roleResource.setRorAddtime(new Date());
//        if(!roleResourceExist(roleResource.getRorRoeid(), roleResource.getRorReeid())){
//            roleResourceMapper.insert(roleResource);
//        }
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteRoleResource(RoleResource roleResource){
//        FormValidator.required(RoleMessageConstant.TITLE_ROLE_ID, roleResource.getRorRoeid());
//        FormValidator.required(ResourceMessageConstant.TITLE_RESOURCE, roleResource.getRorReeid());
//        roleResourceMapper.deleteByValue(roleResource);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteRoleResourceByResourceId(Integer rorReeid){
//        if(rorReeid != null){
//            this.roleResourceMapper.deleteByResourceId(rorReeid);
//        }
//    }
//
//    private boolean roleResourceExist(Integer rorRoeid, Integer rorReeid){
//        return roleResourceMapper.getValueCount(rorRoeid, rorReeid) != 0;
//    }
//
//}
