package com.seeyu.normal.constant.options;

import lombok.Data;

/**
 * @author seeyu
 * @date 2019/1/24
 */
@Data
public class Option {
    private String title;
    private String value;
    private String memo;

    private Option(){
        ;
    }

    public Option(String value, String title){
        this(value, title,null);
    }

    public Option(String value, String title, String memo){
        this.title = title;
        this.value = value;
        this.memo = memo;
    }

    public static Option defaultOption(String title){
        return new DefaultOption(title);
    }

    public static class DefaultOption extends Option{
        public DefaultOption(String title){
            super("", title);
        }
    }

}
