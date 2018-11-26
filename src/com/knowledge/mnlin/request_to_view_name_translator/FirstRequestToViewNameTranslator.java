package com.knowledge.mnlin.request_to_view_name_translator;

import com.knowledge.mnlin.znd.CommonI;

import org.springframework.web.servlet.RequestToViewNameTranslator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/11/12  16:32
 * function : 从 request 中得到  viewname
 * <p>
 * 处理已经经过了具体的controller进行处理,只是没有返回view或者viewname,此时才会自定义该处理
 * <p>
 * 如果根据url没有找到任何的handler去处理,则会返回系统默认的404状态码
 *
 * @author mnlin
 */
public class FirstRequestToViewNameTranslator implements RequestToViewNameTranslator, CommonI {
    @Override
    public String getViewName(HttpServletRequest httpServletRequest) throws Exception {
        getL().info("从request中拿到模版view");

        return "/404.jsp";
    }
}

