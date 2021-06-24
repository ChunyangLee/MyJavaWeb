package com.lichunyang.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static <T> T copyParamsToBean(Map map, T bean){
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return bean;
    }

    public static Integer parseInt(String num, Integer defaultValue){

        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            System.out.println("使用默认值!");
        }
        return defaultValue;
    }

    public static Double parseDouble(String num, Double defaultValue){

        try {
            if(num!=null){
                return Double.parseDouble(num);
            }
        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            System.out.println("使用默认值!");
            System.out.println("double parse 出现问题！");
        }
        return defaultValue;
    }
}
