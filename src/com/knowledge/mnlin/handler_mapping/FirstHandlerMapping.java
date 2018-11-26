package com.knowledge.mnlin.handler_mapping;


import com.knowledge.mnlin.controllers.FirstController;
import com.knowledge.mnlin.znd.CommonI;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/11/12  10:07
 * function : 自定义 Handler-Mapping
 * <p>
 * 路由指定Handler处理器
 *
 * @author mnlin
 */
public class FirstHandlerMapping implements HandlerMapping, Ordered, CommonI {
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest httpServletRequest) throws Exception {
        if (httpServletRequest.getRequestURL().toString().contains("test")) {
            return new HandlerExecutionChain(new FirstController());
        } else {
            getL().info("返回null,表示该HandlerMapping未消费本次请求");
            return null;
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
