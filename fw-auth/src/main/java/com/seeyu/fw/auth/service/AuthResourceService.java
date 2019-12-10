package com.seeyu.fw.auth.service;

import com.seeyu.fw.auth.entity.AuthResource;
import com.seeyu.fw.auth.mapper.AuthResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author seeyu
 * @date 2019/4/30
 */
@Service
public class AuthResourceService {

//    @Autowired
//    private AuthRoleResourceService roleResourceService;
    @Autowired
    private AuthResourceMapper resourceMapper;
//
//    public void deleteResource(Integer reeId){
//        if(reeId != null){
//            this.roleResourceService.deleteRoleResourceByResourceId(reeId);
//            this.resourceMapper.deleteByPrimaryKey(reeId);
//        }
//    }

    public List<AuthResource> getServiceActiveResources(Integer serviceId){
        return this.resourceMapper.selectActiveResourcesByServiceId(serviceId);
    }

    public List<AuthResource> getAccountGrantServiceActiveResources(Integer serviceId, Integer accountId){
        return this.resourceMapper.getAccountGrantServiceActiveResources(serviceId, accountId);
    }



}
