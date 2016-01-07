package com.controller;

import com.model.LogDo;
import com.model.TokenDO;
import com.model.UserBaseDO;
import com.service.TokenService;
import com.tool.MQLogSender;
import com.tool.RedisCacheUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.service.UserBaseService;

import org.json.JSONObject;

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
@ResponseBody
public class RegisterController {
    Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    UserBaseService userBaseService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MQLogSender sender;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String sayHello(@FormParam("userName") String userName, @FormParam("password") String password,
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String phone, @RequestParam String password,
                        ServletRequest request, ServletResponse response) {
        try {
            UserBaseDO userBaseDO = userBaseService.findByPhone(phone);
            if (userBaseDO != null) {
                if (userBaseDO.getPassword().equals(password)) {
                    TokenDO tokenDO = new TokenDO();
                    tokenDO.setUserName(userBaseDO.getUserName());
                    tokenDO.setUserId(userBaseDO.getUserName());
                    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                    tokenDO.setUri(httpServletRequest.getRequestURI());
                    String tokenId = tokenService.addTokenDO(tokenDO);
                    if (null == tokenId) {
                        throw new Exception("生成token失败");
                    };

                    //设置令牌
                    Cookie cookie = new Cookie("tokenId", tokenId);
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.addCookie(cookie);
                    //登录后将数据返回
                    JSONObject jsonObject = new JSONObject(userBaseDO);
                    //将登录日志MQ发送到DB
                    LogDo logDo = new LogDo();
                    logDo.setUserId(userBaseDO.getUserId());
                    logDo.setUri(httpServletRequest.getRequestURI());
                    sender.sender(logDo);
                    return jsonObject.toString();
                } else {
                    return "login fail";
                }
            } else {
                return "login fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "login fail";
        }

    }

    @RequestMapping(value = "/token",method = RequestMethod.GET)
    public String index(@CookieValue("tokenId") String token) throws Exception {
        TokenDO tokenDO = tokenService.findTokenById(token);
        JSONObject jsonObject = new JSONObject(tokenDO);
        return jsonObject.toString();
    }
}
