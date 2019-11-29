//package com.seeyu.fw.auth.service.helper;
//
//import com.jieyundata.micro.auth.server.biz.entity.Resource;
//import com.jieyundata.micro.auth.server.constant.ResourceLevelConstant;
//import com.seeyu.core.constant.ActivationState;
//import com.seeyu.mvc.common.model.SecurityResourceHolderI;
//
///**
// * @author seeyu
// * @date 2019/4/23
// */
//public class SecurityResourceCheckHelper {
//
//    private SecurityResourceHolderI resource;
//
//    public SecurityResourceCheckHelper(Resource resource){
//        this.resource = resource == null ? new EmptySecurityResource() : new SecurityResource(resource);
//    }
//
//    /**
//     * 是否 不需要登录就可以访问
//     * @return
//     */
//    public boolean isLevel1(){
//       return ResourceLevelConstant.LEVEL_1 == resource.getResourceLevel();
//    }
//
//    /**
//     * 是否 登录就可以访问
//     * @return
//     */
//    public boolean isLevel2(){
//       return ResourceLevelConstant.LEVEL_2 == resource.getResourceLevel();
//    }
//
//    /**
//     * 是否 需要分配给用户才可以访问
//     * @return
//     */
//    public boolean isLevel3(){
//        return ResourceLevelConstant.LEVEL_3 == resource.getResourceLevel();
//    }
//
//
//    /**
//     * 是否是禁用资源
//     * @return
//     */
//    public boolean isDisableResource(){
//       return  !this.resource.isActiveState();
//    }
//
//
//    private class EmptySecurityResource implements SecurityResourceHolderI {
//
//        @Override
//        public String getResourceUri() {
//            return "";
//        }
//
//        @Override
//        public Integer getResourceLevel() {
//            return -1;
//        }
//
//        @Override
//        public Integer getResourceActive() {
//            return -1;
//        }
//
//        @Override
//        public boolean isActiveState() {
//            return true;
//        }
//    }
//
//
//    private class SecurityResource implements SecurityResourceHolderI {
//
//        private Resource resource;
//
//        public SecurityResource(Resource resource){
//            this.resource = resource;
//        }
//
//        @Override
//        public String getResourceUri() {
//            return this.resource.getReeUri();
//        }
//
//        @Override
//        public Integer getResourceLevel() {
//            return this.resource.getReeLevel();
//        }
//
//        @Override
//        public Integer getResourceActive() {
//            return this.resource.getReeActive();
//        }
//
//        @Override
//        public boolean isActiveState() {
//            return ActivationState.isActiveState(this.resource.getReeActive());
//        }
//    }
//
//}
