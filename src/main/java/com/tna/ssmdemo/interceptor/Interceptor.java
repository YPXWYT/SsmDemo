package com.tna.ssmdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {

//    后处理
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("************BaseInterceptor afterCompletion executed**********");
    }

//    返回处理
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("************BaseInterceptor postHandle executed**********");
    }

    //    预处理
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        System.err.println("------------------>:已完成跨域处理");
        System.out.println("************BaseInterceptor preHandle executed**********");
        return true;
    }
}
