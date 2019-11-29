//package com.seeyu.fw.auth.service;
//
//import com.jieyundata.micro.auth.server.biz.entity.UserRole;
//import com.jieyundata.micro.auth.server.biz.mapper.UserRoleMapper;
//import com.jieyundata.micro.auth.server.constant.message.AccountMessageConstant;
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
//public class AuthUserRoleService {
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//
//    @Transactional(rollbackFor = Exception.class)
//    public void addUserRole(UserRole userRole){
//        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_ID, userRole.getUreUsrid());
//        FormValidator.required(RoleMessageConstant.TITLE_ROLE_ID, userRole.getUreRoleid());
//        userRole.setUreAddtime(new Date());
//        if(!userRoleExist(userRole.getUreRoleid(), userRole.getUreUsrid())){
//            userRoleMapper.insert(userRole);
//        }
//    }
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteUserRole(UserRole userRole){
//        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_ID, userRole.getUreUsrid());
//        FormValidator.required(RoleMessageConstant.TITLE_ROLE_ID, userRole.getUreRoleid());
//        userRoleMapper.deleteByValue(userRole);
//    }
//
//
//    private boolean userRoleExist(Integer ureRoleid, Integer ureUsrid){
//        return userRoleMapper.getValueCount(ureRoleid, ureUsrid) != 0;
//    }
//
//}
