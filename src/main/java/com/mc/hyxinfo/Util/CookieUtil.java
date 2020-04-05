package com.mc.hyxinfo.Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie的工具类
 *
 * @author admin
 */
public class CookieUtil {
    /*设置cookie*/
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /*获取cookie*/
    public static Cookie get(HttpServletRequest request,
                             String name) {
        /*得到request是一个数组,需要进行遍历*/
        Map<String, Cookie> cookieMap = readCookieMap(request);
        /*判断cookie里面的name是否有值*/
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }else{
            return null;
        }

    }

    /*将cookie封装成map*/
    public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies
            ) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
       return cookieMap;
    }
}
