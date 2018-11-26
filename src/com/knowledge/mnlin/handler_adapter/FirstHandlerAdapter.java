package com.knowledge.mnlin.handler_adapter;

import com.knowledge.mnlin.znd.CommonI;
import com.knowledge.mnlin.controllers.FirstController;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/11/12  10:54
 * function : handler适配器
 * <p>
 * 在handler-mapping找到handler后,指定 handler 如何处理,做中间适配
 *
 * @author mnlin
 */
public class FirstHandlerAdapter implements HandlerAdapter, CommonI, Ordered {
    @Override
    public boolean supports(Object o) {
        getL().info("是否支持某个handler来处理");
        return o instanceof FirstController;
    }

    @Override
    public ModelAndView handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        getL().info("处理具体逻辑,返回ModelAndView类型");
        return ((FirstController) o).deal(httpServletRequest, httpServletResponse);
    }

    /**
     * 返回 -1 表示不使用缓存
     */
    @Override
    public long getLastModified(HttpServletRequest httpServletRequest, Object o) {
        getL().info("返回上次修改时间");
        return -1;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
