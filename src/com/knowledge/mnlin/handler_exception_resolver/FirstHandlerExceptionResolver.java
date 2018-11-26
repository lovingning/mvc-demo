package com.knowledge.mnlin.handler_exception_resolver;

import com.knowledge.mnlin.znd.CommonI;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/11/12  13:51
 * function : 解析request出现异常 进行处理
 *
 * @author mnlin
 */
public class FirstHandlerExceptionResolver implements HandlerExceptionResolver, CommonI, Ordered {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        getL().info("出现异常");
        e.printStackTrace();
        Map<String, String> map = new HashMap<>();
        map.put("msg", e.toString());
        return new ModelAndView("go.jsp", map);
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
