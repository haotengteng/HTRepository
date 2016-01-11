package com.filter;

import com.ApiProvider;
import com.tool.cookie.Cookiehandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/8.
 */

public class LoginFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Map<String, String> map = null;
        if (httpServletRequest.getCookies() != null) {
            map = Cookiehandler.cookieHandle(httpServletRequest.getCookies());
            if (map != null) {
                if (ApiProvider.tokenService.findTokenById(map.get("tokenId")) != null) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    OutputStream outputStream = servletResponse.getOutputStream();
                    outputStream.write("请登录。。。".getBytes("UTF-8"));
                }
            } else {
                OutputStream outputStream = servletResponse.getOutputStream();
                outputStream.write("请登录。。。".getBytes("UTF-8"));
            }
        } else if ("/account/login".equals(httpServletRequest.getRequestURI())
                || "/account/register".equals(httpServletRequest.getRequestURI())) {

            filterChain.doFilter(servletRequest, httpServletResponse);
        } else {
            //如何在过滤器中被拦截后返回拦截原因
            OutputStream outputStream = servletResponse.getOutputStream();
            outputStream.write("请登录。。。".getBytes("UTF-8"));
        }
    }
    public void destroy() {

    }
}
