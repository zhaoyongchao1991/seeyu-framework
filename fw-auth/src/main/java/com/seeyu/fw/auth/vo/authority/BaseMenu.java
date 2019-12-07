package com.seeyu.fw.auth.vo.authority;

import lombok.Data;
import lombok.ToString;

/**
 * @author seeyu
 * @date 2019/5/5
 */
@Data
@ToString
public class BaseMenu {
    protected Integer id;
    protected String name;
    protected String text;
    protected String uri;
    protected String icon;
    protected String remark;
    protected Integer parentId;
}
