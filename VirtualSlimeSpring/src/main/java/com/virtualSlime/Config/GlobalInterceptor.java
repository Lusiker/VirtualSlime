package com.virtualSlime.Config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object obj) throws Exception{
        System.out.println(request.getRemoteHost());
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath());
        return request.getRemoteHost().equals("127.0.0.1");
    }
}
