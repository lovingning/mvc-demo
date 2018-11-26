package com.knowledge.mnlin.znd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2018/11/7  15:11
 * function : test
 *
 * @author mnlin
 */
public class TestAServlet extends HttpServlet implements CommonI {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        /*var a =req.getRequestDispatcher("/gc/abc");ing
        a.forward(req,resp );*/
    }

    /**
     * 异步处理
     */
    @Override
    @SuppressWarnings("all")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getL().info(System.currentTimeMillis() + ": ::::::::接收到请求" + Thread.currentThread().getId());
        var async = req.startAsync();
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
        async.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

            }
        });
        async.start(runable);

        getL().info("get异步请求:访问abc");
        getL().info(System.currentTimeMillis() + ": ::::::::返回请求" + Thread.currentThread().getId());
        PrintWriter writer = resp.getWriter();
        writer.write("first");
        writer.flush();
    }
}
