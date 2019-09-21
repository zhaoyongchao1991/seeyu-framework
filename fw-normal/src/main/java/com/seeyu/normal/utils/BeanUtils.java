package com.seeyu.normal.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author seeyu
 * @date 2019/4/16
 */
public class BeanUtils {


    /**
     * 把 from 的属性拷贝到 to 中, 必须要求属性一致否则抛出异常
     * @param from
     * @param to
     */
    public static void wrap(Object from, Object to){
        if(from == null){
            return;
        }
        org.springframework.beans.BeanUtils.copyProperties(from, to);
    }


    public static Map<String, Object> transBean2Map(Object obj, Map<String, Object> map) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String name = property.getName();
            if("class".equals(name)){
                continue;
            }
            map.put(name, property.getReadMethod().invoke(obj));
        }
        return map;
    }


    public static Object transMap2Bean(Map<String, Object> map, Object obj) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (map.containsKey(key)) {
                property.getWriteMethod().invoke(obj, map.get(key));
            }
        }
        return obj;
    }

}