//package com.seeyu.fw.auth.service.helper;
//
//import com.jieyundata.micro.auth.common.vo.ServiceAuthority;
//import com.jieyundata.micro.auth.common.vo.auth.MenuRegister;
//import com.jieyundata.micro.auth.common.vo.auth.ResourceRegister;
//import com.jieyundata.micro.auth.common.vo.auth.ServiceRegister;
//import com.jieyundata.micro.auth.server.biz.entity.Service;
//import com.jieyundata.micro.auth.server.biz.mapper.ServiceMapper;
//import com.seeyu.core.utils.Alert;
//import com.seeyu.normal.utils.FormValidator;
//import com.seeyu.normal.utils.FormValidator.ValidateType;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author seeyu
// * @date 2019/5/11
// */
//@Slf4j
//@Component
//public class AuthorityServiceRegisterHelper {
//
//    @Autowired
//    private AuthorityServiceRegisterSaveHelper authoritySaveHelper;
//    @Autowired
//    private AuthorityServiceHelper authorityServiceHelper;
//    @Autowired
//    private ServiceMapper serviceMapper;
//
//    public void register(ServiceAuthority serviceAuthority){
//        validate(serviceAuthority);
//        Service service;
//        if((service = serviceMapper.getServiceByName(serviceAuthority.getSeeName())) != null){
//            log.debug("服务:{}已经存在, 更新权限", serviceAuthority.getSeeName());
//            this.authoritySaveHelper.updateServiceAuthority(serviceAuthority, service);
//        }
//        else{
//            log.debug("服务:{}不存在, 保存权限", serviceAuthority.getSeeName());
//            service = this.authoritySaveHelper.saveServiceAuthority(serviceAuthority);
//        }
//        this.authorityServiceHelper.reloadResources(service.getSeeId());
//    }
//
//    private void validate(ServiceAuthority serviceAuthority){
//        FormValidator.required("服务注册对象", serviceAuthority);
//        validateServiceRegister(serviceAuthority);
//        if(serviceAuthority.getMenus() != null){
//            for(MenuRegister menuRegister : serviceAuthority.getMenus()){
//                validateMenuRegister(menuRegister);
//            }
//        }
//        if(serviceAuthority.getResources() != null){
//            for(ResourceRegister resourceRegister : serviceAuthority.getResources()){
//                validateResourceRegister(resourceRegister);
//            }
//        }
//    }
//
//    private void validateServiceRegister(ServiceRegister serviceRegister){
//        FormValidator.required("是否是系统服务", serviceRegister.getSeeSystem());
//        FormValidator.range("是否是系统服务", serviceRegister.getSeeSystem(), 0,1);
//
//        FormValidator.validate("服务名称", serviceRegister.getSeeName(), ValidateType.required, ValidateType.noBlank);
//        FormValidator.rangeLength("服务名称", serviceRegister.getSeeName(), 0, 50);
//
//        FormValidator.validate("服务文本", serviceRegister.getSeeText(), ValidateType.required, ValidateType.noBlank);
//        FormValidator.rangeLength("服务文本", serviceRegister.getSeeText(), 0, 20);
//
//        FormValidator.notBlank("服务描述", serviceRegister.getSeeInfo());
//        FormValidator.rangeLength("服务描述", serviceRegister.getSeeInfo(), 0, 225);
//    }
//
//    private void validateMenuRegister(MenuRegister menuRegister){
//        FormValidator.validate("菜单名称", menuRegister.getMeuName(), ValidateType.required, ValidateType.noBlank);
//        FormValidator.rangeLength("菜单名称", menuRegister.getMeuName(), 0, 50);
//
//        FormValidator.validate("菜单文本", menuRegister.getMeuText(), ValidateType.required, ValidateType.noBlank);
//        FormValidator.rangeLength("菜单文本", menuRegister.getMeuText(), 0, 20);
//    }
//
//    private void validateResourceRegister(ResourceRegister resourceRegister){
//        FormValidator.validate("资源名称", resourceRegister.getReeName(), ValidateType.required, ValidateType.noBlank);
//        FormValidator.rangeLength("资源名称", resourceRegister.getReeName(), 0, 50);
//
//        FormValidator.validate("资源地址", resourceRegister.getReeUri(), ValidateType.required, ValidateType.noBlank);
//        FormValidator.rangeLength("资源地址", resourceRegister.getReeUri(), 0, 100);
//        if(!resourceRegister.getReeUri().startsWith("/")){
//            Alert.alert("资源地址必须以\"/\"开头");
//        }
//    }
//
//}
