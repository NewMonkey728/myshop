package com.qf.myshop.servlet;

import com.qf.myshop.common.Const;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.UserServices;
import com.qf.myshop.service.impl.UserServicesImpl;
import com.qf.myshop.tool.Base64Utils;
import com.qf.myshop.tool.RandomUtils;
import org.omg.CORBA.Request;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Lonely
 */
@WebServlet("/UserController")
public class UserController extends BaseServlet {

    //子类没有services
    //实例业务层的实现类对象
    private UserServices us = new UserServicesImpl();

    /**
     * 处理登录业务
     * @param request
     * @param response
     * @return
     */
    public String login(HttpServletRequest request, HttpServletResponse response) {
       //接收请求中的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String auto=request.getParameter("auto");


        //调用业务层处理请求，并返回结果
        User u=us.login(username,password);
        if (u==null) {
            return Const.REDIRECT + "/login.jsp";
        }
        //用户激活成功，将登录信息保存在session中
        request.getSession().setAttribute(Const.UserConst.IS_LOGIN,u);
        //判断用户登录时是否勾选两周内自动登录选项

        if ("on".equals(auto)) {
            //先保存用户名和密码
            String value=u.getUsername()+":"+u.getUpassword();
            //将用户名和密码保存到Cookie里面
            Cookie c=new Cookie(Const.UserConst.IS_LOGIN, Base64Utils.encode(value));
            c.setPath("/");
            c.setMaxAge(60*60*24*14);
            //最后写给浏览器
            response.addCookie(c);
        }
        return Const.REDIRECT + "/index.jsp";

}

    /**
     * 处理注册业务
     * @param request
     * @param response
     * @return
     */
    public String reg(HttpServletRequest request, HttpServletResponse response) {
    //1获取用户提交的数据
    String uname = request.getParameter("username");
    String upassword = request.getParameter("upassword");
    String usex = request.getParameter("usex");
    String uemail = request.getParameter("email");
    //2封装到对象里面, 封装用户数据还需要
    User u = new User(uname, upassword, usex, uemail, Const.UserConst.NOT_ACTIVE,
            RandomUtils.createActive(), Const.UserConst.NOT_ADMIN);

    //3调用业务层进行新增的业务处理
    int i = us.register(u);

    //4根据结果不成功跳转message.jsp页面并展示信息,成功就跳转registerSuccess.jsp
    if (i > 0) {
        return Const.REDIRECT + "/registerSuccess.jsp";
    }
    //失败给提示信息到message页面
    request.setAttribute("meg", "注册失败,请稍后再试");
    return Const.FORWARD + "/message.jsp";
}

/**
 * 验证用户名是否存在
 */
public String checkName(HttpServletRequest request, HttpServletResponse response){
    //接收请求中的数据
    String name= request.getParameter("username");

    //直接调用业务层查找用户名是否存在
    User u=us.checkName(name);
    System.out.println(u);
    if (u==null) {
        return "0";
    }
    return "1";
}

    /***
     * 判断method的如果为空的话，就跳到主页面
     * @param request
     * @param response
     * @return
     */
    public String index(HttpServletRequest request, HttpServletResponse response){
    return Const.REDIRECT+"/index.jsp";
}

    /**
     * 处理邮件激活的业务
     * @return
     */
    public String activate(HttpServletRequest request, HttpServletResponse response){
        //接收用户信息 e（emile） c（code）
        String email=request.getParameter("e");
        String code=request.getParameter("c");


        //调用业务层用email和code查询用户
        //返回值如果为1 激活 0激活失败 2没有账号 3该用户已经激活
        int i = us.activate(email,code);

        String msg="";
        if (i==0) {
            msg="激活失败！";
        }else if (i==1){
            msg="恭喜激活成功可以开始剁手！";
        }else if (i==2){
            msg="抱歉你的账户不存在！";
        }else if (i==3){
            msg="你已经激活过了！";
        }
        request.setAttribute("msg",msg);
        return Const.FORWARD+"/message.jsp";
}

    /**
     * 用户注销
     */
 public String logOut(HttpServletRequest request, HttpServletResponse response){
       //清除session中的数据
        request.getSession().invalidate();
        //另一种方法是移除设置的属性值
        request.removeAttribute(Const.UserConst.IS_LOGIN);
        return Const.REDIRECT+"/index.jsp";
 }


}
