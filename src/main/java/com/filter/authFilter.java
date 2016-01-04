package com.filter;

import com.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by htt on 2015/12/30.
 */
public class AuthFilter implements Filter {

    @Autowired
    TokenService tokenService;
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
       if ("tokenId".equals(httpServletRequest.getCookies()[0].getName())){
           if (tokenService.findTokenById(httpServletRequest.getCookies()[0].getValue())!=null){
               return  ;
           }
       }
    }

    public void destroy() {

    }
}
