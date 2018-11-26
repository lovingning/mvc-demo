package com.knowledge.mnlin.znd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/11/15  16:41
 * function :
 *

 * @author mnlin
 */
@ControllerAdvice(basePackages = {"com.knowledge.mnlin.znd"})
public class BaseController {
    /**
     * 处理全局异常信息
     * <p>
     * 即便有异常,也返回满足格式的数据
     */
    @ExceptionHandler({ClassCastException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void handlerExceptions(Exception exception, HttpServletResponse response) {
        if (exception != null) {
            exception.printStackTrace();
            try {
                response.getWriter().write("ClassCastException bala......");
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.addHeader("error", exception.getMessage());
        }
    }

}
