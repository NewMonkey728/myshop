package com.qf.myshop.servlet;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@WebServlet("/code")
public class CodeServlet extends BaseServlet {
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1,生成验证码对象(宽，高，验证码个数，验证码干扰线条数)
        ValidateCode validateCode = new ValidateCode(100, 35, 5, 20);
        //2,将生成的验证码保存到session中
        String code = validateCode.getCode();
        System.out.println(code);
        request.getSession().setAttribute("code", code);
        //3,将生成的验证码写到页面上
        ServletOutputStream ouputStream = response.getOutputStream();
        validateCode.write(ouputStream);
    }

}
