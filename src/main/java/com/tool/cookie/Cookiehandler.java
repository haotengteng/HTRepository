package com.tool.cookie;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

/**
 * 将cookie数组处理成map
 * Created by Administrator on 2016/1/8.
 */

public class Cookiehandler {
    public static Map<String,String> cookieHandle(Cookie[] cookies){
        Map<String,String> map = new HashMap<String, String>();
        for (Cookie cookie:cookies){
            map.put(cookie.getName(), cookie.getValue());
        }
        return map;
    }
}
