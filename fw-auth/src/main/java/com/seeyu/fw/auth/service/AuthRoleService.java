package com.seeyu.fw.auth.service;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.core.utils.Alert;
import com.seeyu.core.utils.Assert;
import com.seeyu.fw.auth.constant.message.RoleMessageConstant;
import com.seeyu.fw.auth.constant.message.SystemMessageConstant;
import com.seeyu.fw.auth.entity.AuthRole;
import com.seeyu.fw.auth.exception.EntityAlreadyInUsedException;
import com.seeyu.fw.auth.mapper.AuthRoleMapper;
import com.seeyu.fw.auth.service.helper.AuthRoleServiceHelper;
import com.seeyu.fw.auth.vo.AuthRoleAddModel;
import com.seeyu.fw.auth.vo.AuthRoleEditModel;
import com.seeyu.normal.utils.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/4/29
 */
@Service
public class AuthRoleService {

    @Autowired
    private AuthAccountRelRoleService accountRelRoleService;
    @Autowired
    private AuthRoleRelResourceService roleRelResourceService;
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


    public List<AuthRole> getAccountGrantActiveRoles(Integer actId){
        return this.roleMapper.selectAccountGrantActiveRoleList(actId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Integer roleId) throws EntityAlreadyInUsedException {
       this.deleteRole(roleId, false);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleForce(Integer roleId){
        try {
            this.deleteRole(roleId, true);
        } catch (EntityAlreadyInUsedException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void refreshRoleRelResourceRelations(Integer roleId, List<Integer> resourceIds, String actionUser){
       this.roleRelResourceService.refreshRelations(roleId, resourceIds, actionUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editRoleRelResourceRelations(Integer roleId, List<Integer> addResourceIds, List<Integer> removeResourceIds, String actionUser){
        this.roleRelResourceService.addRelation(roleId, addResourceIds, actionUser);
        this.roleRelResourceService.deleteRelations(roleId, removeResourceIds);
    }

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
        this.roleMapper.updateByPrimaryKeySelective(role);
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

    private void deleteRole(Integer roleId, boolean force) throws EntityAlreadyInUsedException {
        Assert.notNull(roleId, "roleId不能为空");
        if(!force){
            this.checkRelations(roleId);
        }
        //删除角色资源对应关系
        this.roleRelResourceService.deleteRelationByRole(roleId);
        this.accountRelRoleService.deleteRelationByRole(roleId);
        this.roleMapper.deleteByPrimaryKey(roleId);
    }

    private void checkRelations(Integer roleId) throws EntityAlreadyInUsedException {
        if(this.accountRelRoleService.existRelation(roleId)){
            throw new EntityAlreadyInUsedException("角色已经分配给用户");
        }
    }

}
