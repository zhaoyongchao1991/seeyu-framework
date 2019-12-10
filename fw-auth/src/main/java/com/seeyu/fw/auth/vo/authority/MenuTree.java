package com.seeyu.fw.auth.vo.authority;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MenuTree extends BaseMenu {
    private Integer level;
    private List<MenuTree> subMenus = null;
}