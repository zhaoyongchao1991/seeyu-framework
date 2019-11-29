//package com.seeyu.fw.auth.service;
//
//import com.jieyundata.micro.auth.common.vo.auth.ServiceInfo;
//import com.jieyundata.micro.auth.server.biz.entity.Service;
//import com.jieyundata.micro.auth.server.biz.mapper.ServiceMapper;
//import com.jieyundata.micro.auth.server.constant.message.ServiceMessageConstant;
//import com.jieyundata.micro.auth.server.constant.message.SystemMessageConstant;
//import com.seeyu.core.constant.ActivationState;
//import com.seeyu.core.utils.Alert;
//import com.seeyu.normal.utils.FormValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author seeyu
// * @date 2019/5/5
// */
//@org.springframework.stereotype.Service
//public class AuthServiceService {
//
//    @Autowired
//    private ServiceMapper serviceMapper;
//
//
//    public List<Service> getAllServices(){
//        return this.serviceMapper.getAllServices();
//    }
//
//    public ServiceInfo getService(String seeName){
//        FormValidator.required(ServiceMessageConstant.TITLE_SERVICE_NAME, seeName);
//        Service service = serviceMapper.getServiceByName(seeName);
//        if(service == null){
//            return null;
//        }
//        ServiceInfo serviceInfo = new ServiceInfo();
//        serviceInfo.setSeeId(service.getSeeId());
//        serviceInfo.setSeeName(service.getSeeName());
//        return serviceInfo;
//    }
//
//
//    public void enable(Integer seeId) {
//        Service service = serviceMapper.selectByPrimaryKey(seeId);
//        Alert.alert(service != null, SystemMessageConstant.OBJECT_NOT_EXIST, ServiceMessageConstant.TITLE_SERVICE);
//        service.setSeeActive(ActivationState.getActiveState());
//        fillService(service);
//        serviceMapper.updateByPrimaryKey(service);
//    }
//
//
//    public void disable(Integer seeId) {
//        Service service = serviceMapper.selectByPrimaryKey(seeId);
//        Alert.alert(service != null, SystemMessageConstant.OBJECT_NOT_EXIST, ServiceMessageConstant.TITLE_SERVICE);
//        service.setSeeActive(ActivationState.getDisableState());
//        fillService(service);
//        serviceMapper.updateByPrimaryKey(service);
//    }
//
//
//    private void fillService(Service service) {
//        service.setSeeModifyTime(new Date());
//        if(service.getSeeId() == null){
//            service.setSeeAddTime(service.getSeeModifyTime());
//            service.setSeeActive(ActivationState.getActiveState());
//        }
//    }
//}
