package com.seeyu.fw.auth.service;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.fw.auth.constant.message.RoleMessageConstant;
import com.seeyu.fw.auth.entity.AuthRole;
import com.seeyu.fw.auth.mapper.AuthRoleMapper;
import com.seeyu.fw.auth.service.helper.AuthRoleServiceHelper;
import com.seeyu.fw.auth.vo.AuthRoleAddModel;
import com.seeyu.fw.auth.vo.AuthRoleEditModel;
import com.seeyu.normal.utils.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author seeyu
 * @date 2019/4/29
 */
@Service
public class AuthRoleService {

    @Autowired
    private AuthRoleServiceHelper roleServiceHelper;
    @Autowired
    private AuthRoleMapper roleMapper;
//    @Autowired
//    private AuthRoleResourceService roleResourceService;
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//    @Autowired
//    private RoleResourceMapper roleResourceMapper;
//    @Autowired
//    private AuthorityServiceHelper authorityServiceHelper;


//    public Role getRole(Integer id){
//        FormValidator.required(RoleMessageConstant.TITLE_ROLE_ID, id);
//        return roleMapper.selectByPrimaryKey(id);
//    }


//    public RoleResourceOrm getRoleWithResources(Integer id){
//        FormValidator.required(RoleMessageConstant.TITLE_ROLE_ID, id);
//        return roleMapper.selectRoleResourceOrmByRoleId(id);
//    }


//    public GridData grid(RoleTo roleTo, Page page){
//        Role role = new Role();
//        BeanUtils.wrap(roleTo, role);
//        page.setDefaultOrderBy("roeAddtime desc").startPage();
//        return GridData.create((com.github.pagehelper.Page)roleMapper.searchRoleList(role));
//    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer roeId){
        FormValidator.required(RoleMessageConstant.TITLE_ROLE_ID, roeId);
        roleMapper.deleteByPrimaryKey(roeId);
        //userRoleMapper.deleteRoleRelation(roeId);
        //roleResourceMapper.deleteRoleRelation(roeId);
    }

//    @Transactional(rollbackFor = Exception.class)
//    public void refreshRoleResources(Integer roeId, Integer[] resourceIds){
//        Alert.alert(roleMapper.selectByPrimaryKey(roeId) != null, SystemMessageConstant.OBJECT_NOT_EXIST, RoleMessageConstant.TITLE_ROLE);
//        roleResourceMapper.deleteByRoleId(roeId);
//        if(resourceIds != null){
//            for (Integer resId : resourceIds){
//                RoleResource roleResource = new RoleResource();
//                roleResource.setRorRoeid(roeId);
//                roleResource.setRorReeid(resId);
//                roleResourceService.addRoleResource(roleResource);
//            }
//        }
//        //修改角色后刷新资源， 效率非常低 需重构
//        this.authorityServiceHelper.reloadResources();
//    }

//    @Transactional(rollbackFor = Exception.class)
//    public void editRoleResources(Integer roeId, Integer[] addResourceIds, Integer[] removeResourceIds){
//        Alert.alert(roleMapper.selectByPrimaryKey(roeId) != null, SystemMessageConstant.OBJECT_NOT_EXIST, RoleMessageConstant.TITLE_ROLE);
//        if(addResourceIds != null){
//            for (Integer resId : addResourceIds){
//                RoleResource roleResource = new RoleResource();
//                roleResource.setRorRoeid(roeId);
//                roleResource.setRorReeid(resId);
//                roleResourceService.addRoleResource(roleResource);
//            }
//        }
//        if(removeResourceIds != null){
//            for (Integer resId : removeResourceIds){
//                RoleResource roleResource = new RoleResource();
//                roleResource.setRorRoeid(roeId);
//                roleResource.setRorReeid(resId);
//                roleResourceService.deleteRoleResource(roleResource);
//            }
//        }
//    }

//    @Transactional(rollbackFor = Exception.class)
//    public void enable(Integer roeId){
//        Role role = roleMapper.selectByPrimaryKey(roeId);
//        Alert.alert(role != null, SystemMessageConstant.OBJECT_NOT_EXIST, RoleMessageConstant.TITLE_ROLE);
//        role.setRoeActive(ActivationState.getActiveState());
//        roleServiceHelper.fillRole(role);
//        roleMapper.updateByPrimaryKey(role);
//    }


//    @Transactional(rollbackFor = Exception.class)
//    public void disable(Integer roeId){
//        Role role = roleMapper.selectByPrimaryKey(roeId);
//        Alert.alert(role != null, SystemMessageConstant.OBJECT_NOT_EXIST, RoleMessageConstant.TITLE_ROLE);
//        role.setRoeActive(ActivationState.getDisableState());
//        roleServiceHelper.fillRole(role);
//        roleMapper.updateByPrimaryKey(role);
//    }


    @Transactional(rollbackFor = Exception.class)
    public void editRole(AuthRoleEditModel editModel, String actionUser){
        AuthRole role = new AuthRole();
        role.setRoleId(editModel.getRoleId());
        role.setRoleName(editModel.getRoleName());
        role.setRoleText(editModel.getRoleText());
        role.setRoleRemark(editModel.getRoleRemark());
        role.setRoleModifyUser(actionUser);
        role.setRoleModifyTime(new Date());

        //roleServiceHelper.validateRole(role);
        //roleServiceHelper.fillRole(role);
        this.roleMapper.updateByPrimaryKey(role);
    }


//    @Transactional(rollbackFor = Exception.class)
//    public void editRoleAndResources(RoleResourceTo roleResource){
//        this.editRole(roleResource);
//        this.refreshRoleResources(roleResource.getRoeId(), roleResource.getAddResourceIds());
//    }


    @Transactional(rollbackFor = Exception.class)
    public AuthRole addRole(AuthRoleAddModel model, String actionUser){
        AuthRole role = new AuthRole();
        role.setRoleName(model.getRoleName());
        role.setRoleText(model.getRoleText());
        role.setRoleRemark(model.getRoleRemark());
        role.setRoleActive(ActivationState.getActiveState());
        role.setRoleAddUser(actionUser);
        role.setRoleModifyUser(actionUser);
        role.setRoleAddTime(new Date());
        role.setRoleModifyTime(role.getRoleAddTime());

        this.roleServiceHelper.validateRole(role);
        this.roleMapper.insert(role);
        return role;
    }


//    @Transactional(rollbackFor = Exception.class)
//    public Serializable addRoleAndResources(RoleResourceTo roleResource){
//        Serializable roleId = this.addRole(roleResource);
//        this.refreshRoleResources((Integer)roleId, roleResource.getAddResourceIds());
//        return roleId;
//    }


}
