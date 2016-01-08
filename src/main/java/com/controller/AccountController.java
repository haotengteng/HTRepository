package com.controller;

import com.model.LogDo;
import com.model.TokenDO;
import com.model.UserBaseDO;
import com.service.TokenService;
import com.service.UserBaseService;
import com.tool.MQLogSender;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.util.SafeEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by htt on 2015/12/19.
 */
@Controller
@ResponseBody
public class AccountController {
    Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    UserBaseService userBaseService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MQLogSender sender;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String sayHello(@RequestParam("userName") String userName, @RequestParam(value = "password") String password,
                           @RequestParam("phone") String phone) throws Exception {
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
    public String login(@RequestParam(required = false) String phone, @RequestParam String password,
                        ServletRequest request, ServletResponse response) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            UserBaseDO userBaseDO = userBaseService.findByPhone(phone);
            if (userBaseDO != null
                    ||userBaseDO.getPassword().equals(SafeEncoder.encode(password).toString())) {
                    TokenDO tokenDO = new TokenDO();
                    tokenDO.setUserName(userBaseDO.getUserName());
                    tokenDO.setUserId(userBaseDO.getUserId());
                    tokenDO.setPhone(phone);
                    String tokenId = tokenService.addTokenDO(tokenDO);
                    if (null == tokenId) {
                        throw new Exception("生成token失败");
                    }
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
