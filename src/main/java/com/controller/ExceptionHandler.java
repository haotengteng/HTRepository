package com.controller;

import com.commonHandler.defineException.CacheException;
import com.tool.JsonUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by htt on 2015/12/21.
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

     @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String handlerExceptor(Exception e){
       return JsonUtil.getJson(e.getMessage());
   }
    @org.springframework.web.bind.annotation.ExceptionHandler(CacheException.class)
    public String CacheExceptionHandler(CacheException e){
        return JsonUtil.getJson(e.getMessage());
    }
}
