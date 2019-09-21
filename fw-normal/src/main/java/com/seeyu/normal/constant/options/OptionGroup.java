package com.seeyu.normal.constant.options;


import com.seeyu.lang.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/1/25
 */
public class OptionGroup {

    private List<Option> options;


    public OptionGroup(){
        options = new ArrayList<>();
    }

    public OptionGroup(int size){
        options = new ArrayList<>(size);
    }


    public void add(Option option){
        options.add(option);
    }

    public void add(String value, String title){
        options.add(new Option(value, title));
    }

    public List<Option> getOptions(){
        return options;
    }

    public Option findOptionByValue(String value){
        if(value == null){
            return null;
        }
        for(Option opt : options){
            if(value.equals(opt.getValue())){
                return opt;
            }
        }
        return null;
    }

    public Option findOptionByTitle(String title){
        if(title == null){
            return null;
        }
        for(Option opt : options){
            if(title.equals(opt.getTitle())){
                return opt;
            }
        }
        return null;
    }

    public String findValue(String title){
        Option option = findOptionByTitle(title);
        return option == null ? null : option.getValue();
    }

    public String findTitle(String value){
        Option option = findOptionByValue(value);
        return option == null ? null : option.getTitle();
    }


}
