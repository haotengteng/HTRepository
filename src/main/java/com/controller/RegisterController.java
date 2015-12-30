package com.controller;

import com.model.TokenDO;
import com.model.UserBaseDO;
import com.service.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.service.UserBaseService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import java.util.UUID;

/**
 * Created by htt on 2015/12/19.
 */
@Controller
public class RegisterController {
    Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    UserBaseService userBaseService;
    @Autowired
    TokenService tokenService;

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String sayHello(@FormParam("userName") String userName,@FormParam("password") String password,
                           @FormParam("phone") String phone) throws Exception {
        UUID uuid = UUID.randomUUID();
        UserBaseDO userBaseDO = new UserBaseDO();
        userBaseDO.setUserId(uuid.toString());
        userBaseDO.setUserName(userName);
        userBaseDO.setPassword(password);
        userBaseDO.setPhone(phone);
        if (userBaseService.regeist(userBaseDO)) {
            return "success";
        }
        throw new Exception("default");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(@RequestParam(required = true) String phone, @RequestParam String password,
                      ServletRequest request, ServletResponse response){
        try {
            UserBaseDO userBaseDO = userBaseService.findByPhone(phone);
            if (userBaseDO != null) {
                if (userBaseDO.equals(password)) {
                    TokenDO tokenDO = new TokenDO();
                    tokenDO.setUserName(userBaseDO.getUserName());
                    tokenDO.setUserId(userBaseDO.getUserName());
                    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                    tokenDO.setUri(httpServletRequest.getRequestURI());
                    String tokenId = tokenService.addTokenDO(tokenDO);
                    if (null == tokenId) {
                        throw new Exception("生成token失败");
                    }
                    Cookie cookie = new Cookie("tokenId", tokenId);
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.addCookie(cookie);
                }
            }
        }catch (Exception e){
               e.printStackTrace();
        }
    }
    @RequestMapping
    @ResponseBody
    public String index(@CookieParam("tokenId") String token) throws Exception {
       TokenDO tokenDO =  tokenService.findTokenById(token);
        return tokenDO.getUserName();
    }
}
