package com.knowledge.mnlin.znd;

import com.knowledge.mnlin.validators_editors.BBValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.dynalink.NoSuchDynamicMethodException;

/**
 * Created on 2018/11/7  18:24
 * function : 控制器
 *
 * @author mnlin
 */
@Controller
//@Scope("prototype") 保持单例
@SessionAttributes(types = {byte[].class}, names = {"init_key"})
@RequestMapping(value = "/gc")
public class GoController implements EnvironmentAware, CommonI {

    /**
     * 成员制动主日
     */
    @Autowired(required = false)
    private BBValidator validator;

    {
        getL().info("创建了一次 ...");
    }

    private Environment environment;

    @InitBinder
    public void initBinder1(WebDataBinder binder) {
        //注册 编辑器
        var format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));

        //注册 验证器(TODO: 经常出现异常)
        //binder.addValidators(validator);
    }

    /**
     * modelAttribute
     */
    @ModelAttribute
    public void initAttribute1(Model model) {
        model.addAttribute("init_key", "1111111111");
    }

    /**
     * modelAttribute
     */
    @ModelAttribute("init_key")
    public String initAttribute2(Model model) {
        return "222222";
    }

    /**
     * 弹出异常
     * <p>
     * 不同异常,则将由:
     * {@link #handlerExceptions(Exception, HttpServletResponse)}
     * {@link BaseController#handlerExceptions(Exception, HttpServletResponse)}
     * {@link  com.knowledge.mnlin.handler_exception_resolver.FirstHandlerExceptionResolver#resolveException(HttpServletRequest, HttpServletResponse, Object, Exception)}
     * 来处理
     */
    @RequestMapping(value = {"/exp"}, method = {RequestMethod.GET})
    public String exception() {
        var a = Math.random();
        if (a > 0.66) {
            throw new NoSuchDynamicMethodException("NoSuchDynamicMethodException_value");
        } else if (a > 0.33) {
            throw new ClassCastException("ClassCastException_value");
        } else {
            throw new MethodNotAllowedException("MethodNotAllowedException_value", new ArrayList<>());
        }
    }

    /**
     * 处理GET类型的"/index"和”/”请求
     * <p>
     * 匹配 @RequestMapping 注解的是 RequestMappingHandlerMapping
     */
    @RequestMapping(value = {"/index", "/ind"}, method = {RequestMethod.GET})
    public String index(Model model) throws Exception {
        getL().info("get请求:访问index或者ind");
        model.addAttribute("msg", "Go Go Go!");
        return "/index.jsp";
    }

    /**
     * 返回json格式数据
     */
    @RequestMapping(value = {"/abc_json"}, method = {RequestMethod.GET})
    @ResponseBody()
    public BB abc_json(Model model, @Validated BB bb) throws Exception {
        getL().info("get请求:访问abc");
        model.addAttribute("name", "111111".getBytes(StandardCharsets.US_ASCII));
        return new BB(true);
    }

    /**
     * 异步返回数据
     */
    @SuppressWarnings("all")
    @RequestMapping(value = {"/abc_async"}, method = {RequestMethod.GET})
    public BB abc_async(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        getL().info(System.currentTimeMillis() + ": ::::::::接收到请求" + Thread.currentThread().getId());
        var async = request.startAsync();
        var runable = new Runnable() {
            @Override
            public void run() {

                try {
                    getL().info(System.currentTimeMillis() + ": ::::::::子线程开始执行" + Thread.currentThread().getId());
                    Thread.sleep(3000);

                    var a = async.getResponse().getWriter();
                    a.write("5738530573583750");
                    a.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    getL().info(System.currentTimeMillis() + ": ::::::::子线程结束" + Thread.currentThread().getId());
                    async.complete();
                }
            }
        };
        async.setTimeout(5000);
        async.start(runable);

        getL().info("get异步请求:访问abc");
        getL().info(System.currentTimeMillis() + ": ::::::::返回请求" + Thread.currentThread().getId());
        model.addAttribute("BB", new BB(false));
        return new BB(true);
    }

    /**
     * 显示所有可用的参数信息
     * <p>
     * 在session中或@ModelAttribute注解方法中加入的参数,开始时都会放到model中
     */
    @RequestMapping(value = {"/all"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object all(Model model,
                      HttpServletRequest request,
                      HttpServletResponse response,
                      HttpSession session,
                      AAA aaa,
                      @RequestParam(value = "attr", required = false) String attr,
                      @RequestAttribute(value = "init_key", required = false) String init_key,
                      @RequestBody(required = false) String body/*@Validated */,
                      @RequestHeader(required = false, name = "person") String person,
                      @RequestPart(required = false, name = "file1") MultipartFile file1,
                      RedirectAttributes redirectAttributes,
                      SessionStatus sessionStatus
    ) throws Exception {
        model.addAttribute("init_key", "555555555");
        getL().info("get请求:访问all");
        sessionStatus.setComplete();
        return body;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 处理全局异常信息
     * <p>
     * 即便有异常,也返回满足格式的数据
     */
    @ExceptionHandler({NoSuchDynamicMethodException.class})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void handlerExceptions(Exception exception, HttpServletResponse response) {
        if (exception instanceof NoSuchDynamicMethodException) {
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


