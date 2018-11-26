package com.knowledge.mnlin.controllers;

import com.knowledge.mnlin.znd.CommonI;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/11/12  11:11
 * function : 控制器
 * <p>
 * 与 @requestMapping 注解的方法等类似,mvc中c层,用于处理具体逻辑
 *
 * @author mnlin
 */
public class FirstController implements CommonI {
    {
        getL().info("创建:FirstController");
    }

    public ModelAndView deal(HttpServletRequest req, HttpServletResponse res) {
        if (req.getRequestURL().toString().contains("error")) {
            throw new RuntimeException("人为的异常信息,处理的流程为:\nFirstHandlerMapping -> FirstHandlerAdapter -> FirstController -> FirstHandlerExceptionResolver -> FirstViewResolver");
        } else {
            var map = new HashMap<String, String>();
            map.put("msg", "处理过程为:\nFirstHandlerMapping -> FirstHandlerAdapter -> FirstController -> FirstRequestToViewNameTranslator -> FirstViewResolver");
            return new ModelAndView((View) null, map);
        }
    }
}
