package com.seeyu.fw.auth.service;

import com.seeyu.fw.auth.entity.AuthRoleRelResource;
import com.seeyu.fw.auth.mapper.AuthRoleRelResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/12/10
 */
@Service
public class AuthRoleRelResourceService {

    @Autowired
    private AuthRoleRelResourceMapper roleRelResourceMapper;

    @Transactional(rollbackFor = Exception.class)
    void addRelation(Integer roleId, List<Integer> resourceIds, String actionUser){
        if(resourceIds != null){
            for(Integer resourceId : resourceIds){
                this.addRelation(roleId, resourceId, actionUser);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    void addRelation(Integer roleId, Integer resourceId, String actionUser){
        AuthRoleRelResource roleRelResource = new AuthRoleRelResource();
        roleRelResource.setRoleId(roleId);
        roleRelResource.setResourceId(resourceId);
        roleRelResource.setModifyUser(actionUser);
        roleRelResource.setModifyTime(new Date());
        roleRelResource.setAddUser(roleRelResource.getModifyUser());
        roleRelResource.setAddTime(roleRelResource.getModifyTime());
        this.roleRelResourceMapper.insert(roleRelResource);
    }

    @Transactional(rollbackFor = Exception.class)
    void deleteRelations(Integer roleId, List<Integer> resourceIds){
        if(resourceIds != null){
            for(Integer resourceId : resourceIds){
                this.deleteRelation(roleId, resourceId);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    void deleteRelationByRole(Integer roleId){
        this.roleRelResourceMapper.deleteRelationByRole(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    void deleteRelation(Integer roleId, Integer resourceId){
        this.roleRelResourceMapper.deleteRelation(roleId, resourceId);
    }

    @Transactional(rollbackFor = Exception.class)
    void refreshRelations(Integer roleId, List<Integer> resourceIds, String actionUser){
        this.deleteRelationByRole(roleId);
        this.addRelation(roleId, resourceIds, actionUser);
    }


}
