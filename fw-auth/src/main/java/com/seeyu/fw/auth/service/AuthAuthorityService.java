//package com.seeyu.fw.auth.service;
//
//import com.seeyu.core.utils.BaseJsonData;
//import com.seeyu.fw.auth.service.helper.AuthorityServiceHelper;
//import com.seeyu.fw.auth.service.helper.AuthorityServiceRegisterHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Map;
//
///**
// * @author seeyu
// * @date 2019/5/7
// */
//@Slf4j
//@Service
//public class AuthAuthorityService {
//
//    @Autowired
//    private AuthorityServiceHelper authorityServiceHelper;
//    @Autowired
//    private AuthorityServiceRegisterHelper authorityServiceRegisterHelper;
//
//
//    @Transactional(rollbackFor = Exception.class)
//    public void register(ServiceAuthority serviceAuthority){
//        authorityServiceRegisterHelper.register(serviceAuthority);
//    }
//
//
//    public BaseJsonData accessPermission(String token, String uri) {
//        PrincipalData<Map<String, Object>> principalData = authorityServiceHelper.parsePrincipalData(token);
//        return authorityServiceHelper.permission(authorityServiceHelper.parseLoginUserInfo(principalData), uri).setData(principalData);
//    }
//
//
//}
