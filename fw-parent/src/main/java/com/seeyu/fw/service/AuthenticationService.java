package com.seeyu.fw.service;

import com.jieyundata.jointsearchrescuebigdata.biz.dao.mapper.AuthenticationMapper;
import com.jieyundata.jointsearchrescuebigdata.biz.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author seeyu
 * @date 2019/2/12
 */
@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationMapper authenticationMapper;

    public List<Resource> getActiveResources(){
        return authenticationMapper.getActiveResources();
    }


}
