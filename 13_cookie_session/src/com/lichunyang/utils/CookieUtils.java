package com.lichunyang.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static Cookie getCookie(String cookieName, Cookie[] cookieArr){
        if(cookieArr!=null && cookieName!=null && cookieArr.length!=0){
            for (int i = 0; i < cookieArr.length; i++) {
                if(cookieName.equals(cookieArr[i].getName())){
                    return cookieArr[i];
                }
            }
        }
        throw new RuntimeException("无法找到Cookie！");
    }
}
