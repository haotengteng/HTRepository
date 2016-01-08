package com.tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/8.
 */
public  class JsonUtil {

    public static String getJson(String msg){
        Map<String,String> map = new HashMap<String, String>();
        map.put("msg",msg);
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
    }
}
