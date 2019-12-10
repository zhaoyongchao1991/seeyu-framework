package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.mapper.base.BaseAuthMenuMapper;
import com.seeyu.fw.auth.vo.authority.MenuResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMenuMapper extends BaseAuthMenuMapper {

    List<MenuResource> selectActiveMenuResourceListByServiceId(Integer serviceId);

}
