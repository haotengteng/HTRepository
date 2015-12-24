package com.controller;

import com.model.UserBaseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.service.UserBaseService;

import java.util.UUID;

/**
 * Created by htt on 2015/12/19.
 */
@Controller
public class HelloController {
    @Autowired
    UserBaseService userBaseService;

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String sayHello(String userName,String password) throws Exception {
        UUID uuid = UUID.randomUUID();
        UserBaseDO userBaseDO = new UserBaseDO();
        userBaseDO.setUserId(uuid.toString());
        userBaseDO.setUserName(userName);
        userBaseDO.setPassword(password);
        if (userBaseService.regeist(userBaseDO)){
            return "seccess";
        }
        throw new Exception("defult");
    }
    @RequestMapping
    @ResponseBody
    public String index() throws Exception {
        return "hello";
    }

}
