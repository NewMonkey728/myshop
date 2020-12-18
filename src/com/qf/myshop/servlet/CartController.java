package com.qf.myshop.servlet;

import com.qf.myshop.common.Const;
import com.qf.myshop.entity.Cart;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.CartService;
import com.qf.myshop.service.impl.CartServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/cart")
public class CartController extends BaseServlet {

    CartService cs=new CartServiceImpl();
    /**
     * 加入购物车
     * @param request
     * @param response
     * @return
     */
    public String create(HttpServletRequest request,HttpServletResponse response){
        //首先第一步最关键的一点就是判断用户是否存在，，，也就是是否处于登录状态
        User u = (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
        //然后判断从session中能不能接收到用户的对象，如果接收到用户对象为空，则说明用户没有登录，直接跳转登录页面
        if (u==null) {
              return Const.REDIRECT+"/login.jsp";
        }
        //接收商品的价格price和商品的pid
        String pprice =request.getParameter("pprice");
        String pid=request.getParameter("pid");

        //调用业务层将商品添加进购物车
        cs.creatCar(u.getUid(),Double.valueOf(pprice),Integer.valueOf(pid));
        //添加成功进入购物
        return Const.REDIRECT+"/cartSuccess.jsp";
    }

    /**
     * 查看购物车
     * @param request
     * @param response
     * @return
     */
    public String show(HttpServletRequest request,HttpServletResponse response){
        //首先查看购物车时还是判断当前用户登录的状态
         User u= (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
         String uid=request.getParameter("uid");

        if (u==null||uid==null) {
            //如果没有用户登录，则返回登录页面
            return Const.REDIRECT+"/login.jsp";
        }
        //调用业务层处理业务，并返回结果
        //根据用户的id来查询该用户的购物车
        List<Cart> list=cs.LookCart(u.getUid());
        request.setAttribute("list",list);
        return Const.FORWARD+"cart.jsp";
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @return
     */
    public String clear(HttpServletRequest request,HttpServletResponse response){

        //
       String uid= request.getParameter("uid");

        //调用业务层处理业务 并返回结果
        cs.clearCart(uid);
        return Const.FORWARD+"cart.jsp";
    }

    /**
     * 删除购物车中的一个物品
     * @param request
     * @param response
     * @return
     */
    public String delCart(HttpServletRequest request,HttpServletResponse response){

        String cid = request.getParameter("cid");
        User u = (User) request.getSession().getAttribute(Const.UserConst.AUTO_LOGIN);
        //调用业务层处理业务
        cs.delCart(cid);

        return Const.FORWARD+"cart?method=show&uid=u.getUid";
    }

    /**
     * 修改购物车商品数量
     * @param request
     * @param response
     * @return
     */
    public String updateNum(HttpServletRequest request,HttpServletResponse response){
        //首先第一步最关键的一点就是判断用户是否存在，，，也就是是否处于登录状态
        User u = (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
        //然后判断从session中能不能接收到用户的对象，如果接收到用户对象为空，则说明用户没有登录，直接跳转登录页面
        if (u==null) {
            return Const.REDIRECT+"/login.jsp";
        }
        int cid = Integer.valueOf(request.getParameter("cid"));
        int cnum = Integer.valueOf(request.getParameter("cnum"));
        double price = Double.valueOf(request.getParameter("price"));
        String num = request.getParameter("num");

        //减
        if ("1".equals(num)){
            //调用业务层处理减商品数量业务
            cs.subNum(cid,cnum,price);

        //加
        }else if("2".equals(num)){
            //调用业务层处理加商品数量业务
            cs.addNum(cid,cnum,price);
        }
        return Const.FORWARD+"cart?method=show&uid=u.getUid";
    }
}
