package com.seeyu.fw.auth.vo.abst;

import com.seeyu.fw.auth.entity.AuthRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/12/10
 */
@Data
public class AuthRoleAbstractInfo {

    private Integer roleId;
    private String roleName;

    public static List<AuthRoleAbstractInfo> cast(List<AuthRole> sourceList){
        if(sourceList == null){
            return null;
        }
        List<AuthRoleAbstractInfo> list = new ArrayList<>(sourceList.size());
        for(AuthRole e : sourceList){
            list.add(cast(e));
        }
        return list;
    }

    public static AuthRoleAbstractInfo cast(AuthRole source){
        if(source == null){
            return null;
        }
        AuthRoleAbstractInfo target = new AuthRoleAbstractInfo();
        target.setRoleId(source.getRoleId());
        target.setRoleName(source.getRoleName());
        return target;
    }
}
