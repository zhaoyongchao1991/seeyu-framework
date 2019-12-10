package com.seeyu.fw.auth.vo.authority;

import com.seeyu.fw.auth.entity.AuthMenu;
import com.seeyu.fw.auth.entity.AuthResource;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MenuResource extends AuthMenu {

   private AuthResource resource;


}