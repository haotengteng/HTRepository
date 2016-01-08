package com;

import com.service.TokenService;

/**
 * Created by Administrator on 2016/1/8.
 */
public class ApiProvider {
    public static TokenService tokenService = ApplicationContextHelper.getApplicationContext().getBean(TokenService.class);
}
