package com.knowledge.mnlin.multipart_resolver;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/11/13  10:50
 * function : 文件上传处理器
 * <p>
 * 当有包含文件上传的 request 时,需要处理文件内容
 *
 * @author mnlin
 */
public class FirstMultipartResolver extends StandardServletMultipartResolver {
    /**
     * 判断是否是包含file文件
     */
    @Override
    public boolean isMultipart(HttpServletRequest httpServletRequest) {
       return super.isMultipart(httpServletRequest);
    }

    /**
     * HttpServletRequest -> MultipartHttpServletRequest
     */
    @Override
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest httpServletRequest) throws MultipartException {
        return super.resolveMultipart(httpServletRequest);
    }

    /**
     * 清除中间变量
     */
    @Override
    public void cleanupMultipart(MultipartHttpServletRequest multipartHttpServletRequest) {
        super.cleanupMultipart(multipartHttpServletRequest);
    }
}

