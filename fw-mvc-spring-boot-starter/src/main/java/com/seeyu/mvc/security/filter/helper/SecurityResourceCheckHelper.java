package com.seeyu.mvc.security.filter.helper;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.mvc.common.model.SecurityResourceHolderI;
import com.seeyu.mvc.security.constant.ResourceLevelConstant;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * @author seeyu
 */
public class SecurityResourceCheckHelper {

    private final String PATH_SEPARATOR = "/";
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final List<SecurityResourceHolderI> resourceList;
    private final SecurityResourceHolderI resource;

    public SecurityResourceCheckHelper(List<SecurityResourceHolderI> resourceList, String uri){
        this.resourceList = resourceList;
        this.resource = this.findSecurityResource(resourceList, uri);
    }

    /**
     * 是否 不需要登录就可以访问
     * @return
     */
    public boolean isLevel1(){
       return ResourceLevelConstant.LEVEL_PASS == resource.getResourceLevel();
    }

    /**
     * 是否 登录就可以访问
     * @return
     */
    public boolean isLevel2(){
       return ResourceLevelConstant.LEVEL_LOGIN == resource.getResourceLevel();
    }

    /**
     * 是否 需要分配给用户才可以访问
     * @return
     */
    public boolean isLevel3(){
        return ResourceLevelConstant.LEVEL_CONTROL == resource.getResourceLevel();
    }

    /**
     * 是否是禁用资源
     * @return
     */
    public boolean isDisableResource(){
       return  !this.resource.isActiveState();
    }

    private SecurityResourceHolderI findSecurityResource(List<SecurityResourceHolderI> resourceList, String uri){
        for(SecurityResourceHolderI o : resourceList){
            if(this.antPathMatcher.match(this.fixUri(o.getResourceUri()), this.fixUri(uri))){
                return o;
            }
        }
        return new DefaultSecurityResource();
    }

    private String fixUri(String uri){
        return uri.startsWith(PATH_SEPARATOR) ? uri : PATH_SEPARATOR + uri;
    }

    private class DefaultSecurityResource implements SecurityResourceHolderI{

        @Override
        public String getResourceUri() {
            return "";
        }

        @Override
        public Integer getResourceLevel() {
            return ResourceLevelConstant.LEVEL_LOGIN;
        }

        @Override
        public Integer getResourceActive() {
            return ActivationState.getActiveState();
        }

        @Override
        public boolean isActiveState() {
            return true;
        }
    }

}