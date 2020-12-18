package com.qf.myshop.servlet;

import com.alibaba.fastjson.JSON;
import com.qf.myshop.common.Const;
import com.qf.myshop.entity.*;
import com.qf.myshop.service.AddresService;
import com.qf.myshop.service.CartService;
import com.qf.myshop.service.OrdersService;
import com.qf.myshop.service.impl.AddresServiceImpl;
import com.qf.myshop.service.impl.CartServiceImpl;
import com.qf.myshop.service.impl.OrdersServiceImpl;
import com.qf.myshop.tool.RandomUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/order")
public class OrdersController extends BaseServlet {
    OrdersService os=new OrdersServiceImpl();

    /**
     * 微信支付
     * @param request
     * @param response
     * @return
     */
    public String weiXinPayResult(HttpServletRequest request,HttpServletResponse response) {
        String result = request.getParameter("result");
        WeiXin weiXin = JSON.parseObject(result, WeiXin.class);
        //判断订单是否成功

        if (weiXin.getResult().getResult_code().equals("SUCCESS")) {
            //支付成功  修改订单状态的修改 0 改为已支付的1
            String oid = weiXin.getResult().getOut_trade_no();
            os.updateOrderStatusByOid(oid,Const.OrderCode.ORDER_PAY.getCode());
            return Const.REDIRECT+"/order?method=getOrderList";
        }else {
            //调转到message.jsp页面 支付失败
            request.setAttribute("msg", "支付失败,稍后再试");
            return Const.FORWARD + "/message.jsp";
        }
    }

    /**
     * 订单添加收货地址
     * @param request
     * @param response
     * @return
     */
    public String preView(HttpServletRequest request,HttpServletResponse response){
        //接收User对象
        User u = (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
        if (u==null) {
            //如果没有用户登录，则返回登录页面
            return Const.REDIRECT+"/login.jsp";
        }
        int uid = Integer.valueOf(request.getParameter("uid"));
        //调用业务层处理业务
        AddresService as=new AddresServiceImpl();
        CartService cs = new CartServiceImpl();

        List<Address> addressList=as.lookAddres(uid);
        List<Cart> cartList=cs.LookCart(uid);

        request.setAttribute("addressList",addressList);
        request.setAttribute("cartList",cartList);
        return Const.FORWARD+"/order.jsp";
    }

    /**
     * 提交订单
     * @param request
     * @param response
     * @return
     */
    public String createOrder(HttpServletRequest request,HttpServletResponse response){
        //接收User对象
        User u = (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
        if (u==null) {
            //如果没有用户登录，则返回登录页面
            return Const.REDIRECT+"/login.jsp";
        }
        //接收请求中的数据
        int aid = Integer.valueOf(request.getParameter("aid"));
        os.createOrder(u.getUid(),aid);
        return getOrderList(request,response);
    }

    /**
     * 获得订单列表
     * @param request
     * @param response
     * @return
     */
    public String getOrderList(HttpServletRequest request, HttpServletResponse response) {
        //接收User对象
        User u = (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
        if (u==null) {
            //如果没有用户登录，则返回登录页面
            return Const.REDIRECT+"/login.jsp";
        }

        List<Orders> ordersList = os.getOrderList(u.getUid());
        request.setAttribute("ordersList",ordersList);
        return Const.FORWARD+"orderList.jsp";

    }

    /**
     * 订单详情
     * @param request
     * @param response
     * @return
     */
    public String detail(HttpServletRequest request, HttpServletResponse response){
        //接收请求中的数据
        String oid=request.getParameter("oid");

        //调用业务层处理业务,获得业务层的订单数据
        //orderService
        Orders orders=os.getOrderDetaiByoid(oid);
        request.setAttribute("orders",orders);

        return Const.FORWARD+"orderDetail.jsp";
    }
}
