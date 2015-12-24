package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by htt on 2015/12/21.
 */
public class logFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
System.out.print("234567890");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            String addr = servletRequest.getRemoteAddr();
        String host = servletRequest.getRemoteHost();
        int localPort = servletRequest.getLocalPort();
        int remotePort = servletRequest.getRemotePort();
        System.out.println("addr:"+addr+";"+"host:"+host+";"+"localPort:"+localPort+";remotePort:"+remotePort);
    filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}