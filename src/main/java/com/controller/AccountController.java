package com.controller;

import com.model.LogDo;
import com.model.TokenDO;
import com.model.UserBaseDO;
import com.service.TokenService;
import com.service.UserBaseService;
import com.tool.JsonUtil;
import com.tool.MQLogSender;
import com.tool.md5.MD5Util;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
        try {
            if (userBaseService.findByPhone(phone) != null) {
                throw new Exception("该用户已存在");
            }
            UUID uuid = UUID.randomUUID();
            UserBaseDO userBaseDO = new UserBaseDO();
            userBaseDO.setUserId(uuid.toString());
            userBaseDO.setUserName(userName);
            userBaseDO.setPassword(password);
            userBaseDO.setPhone(phone);
            if (userBaseService.regeist(userBaseDO)) {

                return JsonUtil.getJson("注册成功");
            }
             return JsonUtil.getJson("注册失败");
        } catch (Exception e) {
            throw new Exception("服务器错误" + e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(required = false) String phone, @RequestParam String password,
                        ServletRequest request, ServletResponse response) throws Exception {

            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            UserBaseDO userBaseDO = userBaseService.findByPhone(phone);
            if (userBaseDO != null
                    && userBaseDO.getPassword().equals(MD5Util.MD5(password))) {
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
                JSONObject jsonObject = JSONObject.fromObject(userBaseDO);
                if (jsonObject.containsKey("password")){
                    jsonObject.remove("password");
                }
                //将登录日志MQ发送到DB
                LogDo logDo = new LogDo();
                logDo.setUserId(userBaseDO.getUserId());
                logDo.setUri(httpServletRequest.getRequestURI());
                sender.sender(logDo);
                return jsonObject.toString();
            } else {
                throw new Exception("用户名或密码错误");
            }
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public String index(@CookieValue("tokenId") String token) throws Exception {
        TokenDO tokenDO = tokenService.findTokenById(token);
        JSONObject jsonObject = new JSONObject().fromObject(tokenDO);
        return jsonObject.toString();
    }
}
