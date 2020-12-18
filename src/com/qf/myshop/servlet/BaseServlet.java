package com.qf.myshop.servlet;
import com.qf.myshop.common.Const;
import com.qf.myshop.tool.EmailUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.ConstantCallSite;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String method = request.getParameter("method");
            //判断接收到的method是否位空，如果位空的话让他跳到主页面
            //isEmpty()如果字符串长度为 0，则返回 true，否则返回 false
            if (method.isEmpty()) {
                method = "index";
            }
            Class alcass = this.getClass();
            //2 怎么通过class调用方法
            //2.1 获取Method 对象   参数1 方法名称 , 参数2... 要调用的方法的参数列表
            //直接使用获取的method标识的值作为,要调用的方法的名称使用
            Method method1 = alcass.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //通过method对象来调用方法
            //参数1 传入子类实例也就是this,来调用方法,  参数2:参数
            Object invoke = method1.invoke(this, request, response);

            if (invoke != null) {
                //通过返回值来判断是需要做 转发/重定向/使用response写出响应 那个
                //设置统一的返回类型 String,然后给处理的标识如 转发 forward:/xxx.jsp  重定向redirect:xxx.jsp  写出响应的字符串
                String str = (String) invoke;
                //转发
                if (str.contains(Const.FORWARD)) {
                    //设置路径
                    String path = str.substring(str.indexOf(Const.COLON) + 1);
                    request.getRequestDispatcher(path).forward(request, response);
                    //重定向
                } else if (str.contains(Const.REDIRECT)) {
                    String path = str.substring(str.indexOf(Const.COLON) + 1);
                    //response.sendRedirect(path);
                    response.sendRedirect(request.getContextPath() + path);
                    //响应
                } else {
                    response.getWriter().println(str);

                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
