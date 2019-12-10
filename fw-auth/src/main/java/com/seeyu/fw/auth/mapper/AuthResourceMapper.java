package com.seeyu.fw.auth.mapper;

import com.seeyu.fw.auth.entity.AuthResource;
import com.seeyu.fw.auth.mapper.base.BaseAuthResourceMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthResourceMapper extends BaseAuthResourceMapper {

    List<AuthResource> selectActiveResourcesByServiceId(Integer serviceId);

    List<AuthResource> getAccountGrantServiceActiveResources(@Param("serviceId") Integer serviceId, @Param("accountId") Integer accountId);

}