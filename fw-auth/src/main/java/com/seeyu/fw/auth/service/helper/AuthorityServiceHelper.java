//package com.seeyu.fw.auth.service.helper;
//
//import com.jieyundata.micro.auth.common.vo.LoginUserInfo;
//import com.jieyundata.micro.auth.common.vo.PrincipalData;
//import com.jieyundata.micro.auth.common.vo.auth.ServiceInfo;
//import com.jieyundata.micro.auth.server.biz.entity.Resource;
//import com.jieyundata.micro.auth.server.biz.entity.Service;
//import com.jieyundata.micro.auth.server.biz.mapper.ResourceMapper;
//import com.jieyundata.micro.auth.server.biz.mapper.RoleMapper;
//import com.jieyundata.micro.auth.server.biz.service.JwtService;
//import com.jieyundata.micro.auth.server.biz.service.ServiceService;
//import com.jieyundata.micro.auth.server.biz.vo.SecurityResource;
//import com.jieyundata.micro.auth.server.constant.message.SecurityMessageConstant;
//import com.seeyu.core.utils.Assert;
//import com.seeyu.core.utils.BaseJsonData;
//import com.seeyu.mvc.common.constant.ResponseCode;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.StringUtils;
//
//import java.util.Date;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author seeyu
// * @date 2019/5/7
// */
//@Slf4j
//@Component
//public class AuthorityServiceHelper {
//
//    private AntPathMatcher antPathMatcher=new AntPathMatcher();
//
//    @Autowired
//    private ResourceMapper resourceMapper;
//    @Autowired
//    private ServiceService serviceService;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private JwtService jwtService;
//
//    private Map<Integer, List<SecurityResource>> serviceResourcesMap = new Hashtable<>();
//
//
//    public PrincipalData<Map<String, Object>> parsePrincipalData(String token){
//        try {
//            if(StringUtils.isEmpty(token)){
//                return null;
//            }
//            return jwtService.getPrincipal(token);
//        }
//        catch (Exception e){
//            return null;
//        }
//    }
//
//
//    public LoginUserInfo parseLoginUserInfo(PrincipalData<Map<String, Object>> principalData){
//        if(principalData == null || principalData.isExpired()){
//            return null;
//        }
//        try{
//            return principalCast2LoginUserInfo(principalData.getPrincipal());
//        }catch (Exception e){
//            log.error("解析登录用户失败", e);
//            return null;
//        }
//    }
//
//
//    public BaseJsonData permission(LoginUserInfo loginUserInfo, String uri) {
//        SecurityResource requestResource = this.getMatchResource(uri);
//        SecurityResourceCheckHelper securityResourceCheckHelper = new SecurityResourceCheckHelper(requestResource);
//
//        if(isAdmin(loginUserInfo)){
//            log.debug("系统管理管理员访问,跳过安全检查:{}", uri);
//            return BaseJsonData.SUCCESS();
//        }
//
//        //禁用资源不能访问
//        if(securityResourceCheckHelper.isDisableResource()){
//            log.debug("资源被禁用,不能访问: {}", uri);
//            return BaseJsonData.ERROR().setCode(ResponseCode.AUTHORITY_AUTHENTICATION_FAIL).setMessage(SecurityMessageConstant.SERVER_SECURITY_ALERT_DISABLE_RESOURCE);
//        }
//        //不需要登录就可以访问的资源
//        if(securityResourceCheckHelper.isLevel1()){
//            log.debug("不需要登录就能访问: {}", uri);
//            return BaseJsonData.SUCCESS();
//        }
//        //后面的资源都需要登录才能访问
//        if(!isLogin(loginUserInfo)){
//            log.debug("需要登录才能访问: {}", uri);
//            return BaseJsonData.ERROR().setCode(ResponseCode.LOGIN_AUTHENTICATION_FAIL).setMessage(SecurityMessageConstant.SERVER_SECURITY_ALERT_LOGIN_AUTHENTICATION_FAILED);
//        }
//
//        //必须要显式分配 才能放访问的资源
//        if(securityResourceCheckHelper.isLevel3()){
//            if(!checkUserResource(loginUserInfo, requestResource)){
//                log.debug("需要分配权限才能访问: {}", uri);
//                return BaseJsonData.ERROR().setCode(ResponseCode.AUTHORITY_AUTHENTICATION_FAIL).setMessage(SecurityMessageConstant.SERVER_SECURITY_ALERT_AUTHENTICATION_FAILED);
//            }
//        }
//        //登录就可以访问的资源
//        return BaseJsonData.SUCCESS();
//    }
//
//
////    public List<Resource> getAllResource(String uri){
////        Resource resource = new Resource();
////        resource.setReeSeeid(getServiceInfo(uri).getSeeId());
////        return resourceMapper.getResources(resource);
////    }
//
//    public void reloadResources(){
//        for(Service ser : this.serviceService.getAllServices()){
//            this.reloadResources(ser.getSeeId());
//        }
//    }
//
//    public void reloadResources(Integer serviceId){
//        Resource re = new Resource();
//        re.setReeSeeid(serviceId);
//        List<SecurityResource> resources = this.resourceMapper.getSecurityResources(re);
//        this.serviceResourcesMap.put(serviceId, resources);
//    }
//
//    /**
//     * 检查用户角色 是否包含请求资源需要的角色
//     * @param requestResource
//     * @return
//     */
//    private boolean checkUserResource(LoginUserInfo loginUserInfo, SecurityResource requestResource) {
//        if(requestResource == null){
//            return false;
//        }
//        List<Integer> needRoles = requestResource.getGrantRolesIds();
//        List<Integer> grantRoles = loginUserInfo.getRoleIds();
//        if(grantRoles == null ){
//            return false;
//        }
//        for(Integer grantRoleId : grantRoles){
//            if(needRoles.contains(grantRoleId)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    private boolean isLogin(LoginUserInfo loginUserInfo){
//        return loginUserInfo != null;
//    }
//
//
//    private boolean isAdmin(LoginUserInfo loginUserInfo){
//        return loginUserInfo != null && loginUserInfo.isAdmin();
//    }
//
//
//    private SecurityResource getMatchResource(String uri){
//        String resourceUri = this.extractResourceUri(uri);
//        List<SecurityResource> resources = this.serviceResourcesMap.get(this.getServiceInfo(uri).getSeeId());
//        for(SecurityResource resource : resources){
//            if(antPathMatcher.match(resource.getReeUri(), resourceUri)){
//                return resource;
//            }
//        }
//        return null;
//    }
//
//    private ServiceInfo getServiceInfo(String uri){
//        String serviceName = extractServiceName(uri);
//        log.debug("从uri:{}中抽取到serviceName:{}", uri, serviceName);
//        ServiceInfo serviceInfo = serviceService.getService(extractServiceName(uri));
//        Assert.ASSERT(serviceInfo != null, "未找到服务:" + serviceName );
//        return serviceInfo;
//    }
//
//
//    private String extractServiceName(String uri){
//        return uri.substring(1, uri.indexOf("/", 1));
//    }
//
//
//    private String extractResourceUri(String uri){
//        return uri.substring(uri.indexOf("/", 1));
//    }
//
//
//    private LoginUserInfo principalCast2LoginUserInfo(Map<String, Object> principal){
//        LoginUserInfo loginUserInfo = new LoginUserInfo();
//        if(principal.get("actAddTime") != null){
//            loginUserInfo.setActAddTime(new Date((Long) principal.get("actAddTime")));
//        }
//        loginUserInfo.setAdmin((Boolean)principal.get("admin"));
//        loginUserInfo.setActPhone((String)principal.get("actPhone"));
//        loginUserInfo.setActName((String)principal.get("actName"));
//        loginUserInfo.setActId((Integer) principal.get("actId"));
//        loginUserInfo.setActGender((Integer)principal.get("actGender"));
//        loginUserInfo.setActEmail((String)principal.get("actEmail"));
//        loginUserInfo.setActActive((Integer)principal.get("actActive"));
//        loginUserInfo.setActAccount((String)principal.get("actAccount"));
//        loginUserInfo.setInfo((Map)principal.get("info"));
//        loginUserInfo.setRoleIds((List)principal.get("roleIds"));
//        return loginUserInfo;
//    }
//
//}
