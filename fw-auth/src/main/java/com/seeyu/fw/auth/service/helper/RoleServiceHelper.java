package com.seeyu.fw.auth.service.helper;

import com.seeyu.core.utils.Alert;
import com.seeyu.fw.auth.constant.message.RoleMessageConstant;
import com.seeyu.fw.auth.constant.message.SystemMessageConstant;
import com.seeyu.fw.auth.entity.AuthRole;
import com.seeyu.fw.auth.mapper.AuthRoleMapper;
import com.seeyu.normal.utils.FormValidator;
import com.seeyu.normal.utils.FormValidator.ValidateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author seeyu
 * @date 2019/4/29
 */
@Service
public class RoleServiceHelper {

    @Autowired
    private AuthRoleMapper roleMapper;

//    public Role wrapEditRole(RoleTo editRole) {
//        Role role = roleMapper.selectByPrimaryKey(editRole.getRoeId());
//        Alert.alert(role != null, SystemMessageConstant.OBJECT_NOT_EXIST, RoleMessageConstant.TITLE_ROLE);
//        role.setRoeName(editRole.getRoeName());
//        role.setRoeInfo(editRole.getRoeInfo());
//        return role;
//    }


//    public void fillRole(Role role) {
//        role.setRoeModifytime(new Date());
//        if(role.getRoeId() == null){
//            role.setRoeAddtime(role.getRoeModifytime());
//            role.setRoeActive(ActivationState.getActiveState());
//        }
//    }


    public void validateRole(AuthRole role){
        //检查角色名称
        FormValidator.validate(RoleMessageConstant.TITLE_ROLE_NAME, role.getRoleName(), ValidateType.required, ValidateType.noBlank);
        FormValidator.rangeLength(RoleMessageConstant.TITLE_ROLE_NAME, role.getRoleName(), 2, 20);
        //检查角色描述
        FormValidator.notBlank(RoleMessageConstant.TITLE_ROLE_INFO, role.getRoleRemark());
        FormValidator.maxLength(RoleMessageConstant.TITLE_ROLE_INFO, role.getRoleRemark(), 50);
        //检查角色重复性
        Alert.alert(!roleNameExist(role.getRoleId(), role.getRoleName()), SystemMessageConstant.OBJECT_ALREADY_EXIST, RoleMessageConstant.TITLE_ROLE_NAME);
    }


    private boolean roleNameExist(Integer roleId, String roleName){
        return this.roleMapper.getRoleNameCount(roleId, roleName) != 0;
    }


}
