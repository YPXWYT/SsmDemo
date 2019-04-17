package com.tna.ssmdemo.config;


import com.tna.ssmdemo.interceptor.Interceptor;
import com.tna.ssmdemo.interceptor.Interceptor1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Bean
    public HandlerInterceptor getMyInterceptor() {//        getMyInterceptor方法为拦截器实例注入方法。
        return new Interceptor();

    }

    @Bean
    public HandlerInterceptor getMyInterceptor1() {//        getMyInterceptor方法为拦截器实例注入方法。
        return new Interceptor1();

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getMyInterceptor1()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
