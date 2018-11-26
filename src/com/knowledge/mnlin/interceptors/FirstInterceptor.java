package com.knowledge.mnlin.interceptors;

import com.knowledge.mnlin.znd.CommonI;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/11/19  16:35
 * function : springmvc 拦截器
 *
 * @author mnlin
 */
public class FirstInterceptor implements HandlerInterceptor, CommonI {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        getL().info("拦截器:preHandle,返回false时,本次请求就此结束");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        getL().info("拦截器:postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        getL().info("拦截器:afterCompletion");
    }
}

