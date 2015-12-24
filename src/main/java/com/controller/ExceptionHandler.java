package com.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by htt on 2015/12/21.
 */
@ControllerAdvice
public class ExceptionHandler {
   @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
   @ResponseBody
    public String handlerExceptor(Exception e){
       return e.getMessage();
   }
}
