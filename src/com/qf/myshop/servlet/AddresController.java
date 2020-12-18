package com.qf.myshop.servlet;

import com.qf.myshop.common.Const;
import com.qf.myshop.entity.Address;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.AddresService;
import com.qf.myshop.service.impl.AddresServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


@WebServlet("/address")
public class AddresController extends BaseServlet {

    AddresService as=new AddresServiceImpl();
    /**
     * 新增地址操作
     * @param request
     * @param response
     * @return
     */
    public String add(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //接收请求数据---收件人---手机号----详细地址
//        String aname = request.getParameter("aname");
//        String aphone = request.getParameter("aphone");
//        String adetail = request.getParameter("adetail");
        //获取一个用户id，把根据用户信息给新增的地址赋值
        User u = (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
        if (u==null) {
            //如果没有用户登录，则返回登录页面
            return Const.REDIRECT+"/login.jsp";
        }
        //把前端传递的所有参数封装为map结构中key（name），value（value）
        Map<String,String[]> maps=request.getParameterMap();
        Address a=new Address();
        //使用工具包BeanUtils封装数据
        BeanUtils.populate(a,maps);

        a.setUid(u.getUid());
        //设置是否为默认地址，这里新增的都设置为非默认地址state为0
        a.setAstate(Const.OderConst.DEFAULT_NOT_ADDRESS);
        //调用业务层处理新增业务
        as.addAddress(a);

        return Const.FORWARD+"address?method=show";
    }

    /**
     * 用户个人中心展示地址信息
     * @param request
     * @param response
     * @return
     */
    public String show(HttpServletRequest request,HttpServletResponse response){
        //接收请求中的数据
        User u = (User) request.getSession().getAttribute(Const.UserConst.IS_LOGIN);
        if (u==null) {
            //如果没有用户登录，则返回登录页面
            return Const.REDIRECT+"/login.jsp";
        }
        //调用业务层处理业务
        List<Address> list=as.lookAddres(u.getUid());

        request.setAttribute("list",list);
        return Const.FORWARD+"/self_info.jsp";
    }

    /**
     * 删除地址信息
     * @return
     */
    public String delete(HttpServletRequest request,HttpServletResponse response){
       //接收前端传递过来的数据
        int aid = Integer.valueOf(request.getParameter("aid"));
        //调用业务层处理业务，根据aid进行删除操作

        as.deletAddres(aid);
        return Const.FORWARD+"address?method=show";
    }
    /**
     * 修改地址信息
     * @return
     */
    public String update(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //接收前端传递过来的数据
       // int aid = Integer.valueOf(request.getParameter("aid"));
        //因为修改页面需要展示修改前的数据
        //所有可以用Map集合存所有接收的数据
        Map<String,String[]> maps=request.getParameterMap();

        //还要创建一个地址的对象
        Address a=new Address();
        //用工具包BeanUtils自动封装，，把数据转存到Address里面
        BeanUtils.populate(a,maps);

        //然后调用业务层处理业务
        as.updateAddress(a);
        return Const.FORWARD+"address?method=show";
    }

    /**
     * 设置默认地址信息
     * @return
     */
    public String setDefault(HttpServletRequest request,HttpServletResponse response){
        //接收前端传递过来的数据
        int aid = Integer.valueOf(request.getParameter("aid"));
        //调用业务层处理业务，根据aid进行修改设置默认操作
        as.updatesetDefault(aid);
        return Const.FORWARD+"address?method=show";
    }

}
