package com.tna.ssmdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor1 implements HandlerInterceptor {

    //    后处理
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("************BaseInterceptor1 afterCompletion executed**********");
    }

    //    返回处理
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("************BaseInterceptor1 postHandle executed**********");
    }

    //    预处理
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        System.out.println("************BaseInterceptor1 preHandle executed**********");
        return true;
    }
}