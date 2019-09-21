package com.seeyu.mvc;

import lombok.Data;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author seeyu
 * @date 2019/6/27
 */
public class OgnlTest {

    @Data
    static class User {
        private String name;
        private String password;
        Department d;

        public static String getClassName(){
            return "User.class";
        }
    }

    @Data
    static class Department{
        private String dName;
    }


    public static void main(String[] args) throws OgnlException {

        String[] ss = new String[]{};

        System.out.println((Object[])ss);

    }
}
