package com.qf.myshop.servlet;

import com.qf.myshop.common.Const;
import com.qf.myshop.entity.PageBean;
import com.qf.myshop.entity.Product;
import com.qf.myshop.service.ProductService;
import com.qf.myshop.service.impl.ProductServiceImpl;
import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.sql.SQLOutput;
import java.util.List;

@WebServlet("/product")
public class ProductController extends BaseServlet {

    public String show(HttpServletRequest request, HttpServletResponse response){
        //接收请求的类别tid
        String tid=request.getParameter("tid");

        //获得请求中的页码
        String currentPage=request.getParameter("currentPage");
        if (currentPage==null) {
            currentPage="1";
        }

        PageBean<Product> p=new PageBean();
        //把接受到的页码保存到PageBean中
        p.setCurrentPage(Integer.parseInt(currentPage));
        //调用业务层展示PageBean要展示结果
        ProductService ps=new ProductServiceImpl();
        //调用方法处理业务
        List<Product> pageBean=ps.findprolist(tid,p);

        //3.响应即可
        request.setAttribute("pageBean", p);
        return Const.FORWARD + "/goodsList.jsp";
    }
    public String detail(HttpServletRequest request, HttpServletResponse response){
        //接收请求的类别tid
        String pid=request.getParameter("pid");

        //调用业务层处理业务
        ProductService ps=new ProductServiceImpl();
        Product product=ps.QuerylistDetail(pid);

        //根据处理结果响应页面
        request.setAttribute("product",product);
        return Const.FORWARD+"/goodsDetail.jsp";

    }
}
