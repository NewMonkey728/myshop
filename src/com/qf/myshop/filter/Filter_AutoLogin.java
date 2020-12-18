package com.qf.myshop.filter;

import com.qf.myshop.common.Const;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.UserServices;
import com.qf.myshop.service.impl.UserServicesImpl;
import com.qf.myshop.tool.Base64Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 只拦截登录的页面，检查是否有登录
 */
@WebFilter("/login.jsp")
public class Filter_AutoLogin implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;

        //获取Cookie中的值
        Cookie[] cookies=request.getCookies();
        //然后遍历

        String value=null;
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals(Const.UserConst.AUTO_LOGIN)) {
                value=cookie.getValue();
                break;
            }
            //然后判断value值是否为空
            if (value!=null) {
                String decode= Base64Utils.decode(value);
                String username=decode.split(Const.COLON)[0];
                String password=decode.split(Const.COLON)[0];
                //然后做登录
                UserServices us=new UserServicesImpl();
                User user=us.login(username, password);

                if (user!=null){
                    //直接登录
                    request.getSession().setAttribute(Const.UserConst.IS_LOGIN,user);
                    response.sendRedirect(request.getContextPath()+"/login.jsp");
                    return;
                }
                request.setAttribute("msg","您的密码已变更，请重新输入！");
                request.getRequestDispatcher("/message.jsp").forward(request,response);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
