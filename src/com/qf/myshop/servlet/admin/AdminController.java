package com.qf.myshop.servlet.admin;

import com.qf.myshop.common.Const;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.UserServices;
import com.qf.myshop.service.impl.UserServicesImpl;
import com.qf.myshop.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user")
public class AdminController extends BaseServlet {
    private UserServices us = new UserServicesImpl();
    /**
     * 管理员登录
     * @param request
     * @param response
     * @return
     */
    public String adminLogin(HttpServletRequest request, HttpServletResponse response){
        //接收传递过来的数据
        String name=request.getParameter("username");
        String pwd=request.getParameter("password");
        System.out.println(name);
        System.out.println(pwd);
        //调用业务层
        User u=us.login(name,pwd);
        System.out.println(u);
        if (u!=null){
            return Const.REDIRECT+"/admin/admin.jsp";
        }
        return Const.REDIRECT+"/admin/login.jsp";
    }
}
