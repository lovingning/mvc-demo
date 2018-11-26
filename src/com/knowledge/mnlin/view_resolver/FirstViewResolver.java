package com.knowledge.mnlin.view_resolver;

import com.knowledge.mnlin.znd.CommonI;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.Locale;

/**
 * Created on 2018/11/12  15:08
 * function : 根据controller返回的结果,来选择需要渲染的 模版(如jsp文件),以及如何填充数据
 *
 * @author mnlin
 */
class FirstViewResolver implements ViewResolver, Ordered, CommonI {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        getL().info("根据 s 获取用来处理的 view");
        if (s.contains("go")) {
            return new InternalResourceView("do" + s);
        } else {
            return new InternalResourceView(s);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
