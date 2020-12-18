package com.qf.myshop.service.impl;

import com.qf.myshop.common.Const;
import com.qf.myshop.dao.ItemDao;
import com.qf.myshop.dao.OrdersDao;
import com.qf.myshop.dao.impl.ItemDaoImpl;
import com.qf.myshop.dao.impl.OrdersDaoImpl;
import com.qf.myshop.entity.Cart;
import com.qf.myshop.entity.Item;
import com.qf.myshop.entity.Orders;
import com.qf.myshop.service.CartService;
import com.qf.myshop.service.OrdersService;
import com.qf.myshop.tool.RandomUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author QY
 * @date 2020-12-110:13
 **/
public class OrdersServiceImpl implements OrdersService {

    OrdersDao od=new OrdersDaoImpl();

    /**
     * 创建订单业务
     * @param uid
     * @param aid
     */
    @Override
    public void createOrder(int uid, int aid) {
//创建一个订单对象
        Orders o=new Orders();
        //设置订单编号
        o.setOid(RandomUtils.createOrderId());
        //设置用户uid
        o.setUid(uid);
        //设置地址aid
        o.setAid(aid);
        //设置订单生成时间
        o.setOtime(new Date());
        //设置订单状态
        o.setOstate(Const.OrderCode.ORDER_NOT_PAY.getCode());
        //订单金额
        double sum=0;

        //首先要获得一下购物车的数据
        CartService cs=new CartServiceImpl();

        List<Cart> cartList=cs.getCartList(uid);
        //创建一个item集合为一会批量新增做准备
        List<Item> items=new ArrayList<>();
        for (Cart cart:cartList) {
            //给item赋值，，因为item中有orders表中的oid，还有cart表中的pid，count，num
            Item i=new Item(o.getOid(),cart.getPid(),cart.getCcount(),cart.getCnum());

            //从购物车中获取金额存，再存到订单里面
            sum+=cart.getCcount();
            //把添加的item添加到集合中
            items.add(i);
        }
        //获得cart表遍历后的金额以后存到订单中
        o.setOcount(sum);
        //订单表的数据获取完毕后，把订单表保存到数据库中
        //调用业务层处理业务，，要先将订单表添加进去之后才能生成item表
        od.insertOrders(o);
        //4 批量保存item数据
        // 保存订单详情之前要先保存Order订单的数据,因为需要使用到他的oid外键
        ItemDao id = new ItemDaoImpl();
        //oid 1
        id.addItems(items);
        //5 清空购物车
        cs.deleCart(uid);
    }

    /**
     * 订单详情
     * @param oid
     * @return
     */
    @Override
    public Orders getOrderDetaiByoid(String oid) {
       if (oid!=null){
        //查询Orders的数据
        Orders orders=od.getOrderDetailByoid(oid);
        //查询Items的数据，并封装到Orders里面
        ItemDao id = new ItemDaoImpl();
        List<Item> items=id.getItemDetailByoid(oid);
        //orders封装items
        orders.setItems(items);
           return orders;
       }
        return null;
    }

    @Override
    public int updateOrderStatusByOid(String oid,  int status) {
        if (oid!=null) {

            return od.updateOrderStatusByOid( oid,  status);
        }
        return 0;

    }

    /**
     * 获得订单列表
     * @param uid
     * @return
     */
    @Override
    public List<Orders> getOrderList(int uid) {
        if (uid>0) {
            return od.getOrderList(uid);
        }
        return null;
    }
}
