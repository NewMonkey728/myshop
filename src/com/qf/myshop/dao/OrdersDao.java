package com.qf.myshop.dao;

import com.qf.myshop.entity.Orders;

import java.util.List;

public interface OrdersDao {

    int insertOrders(Orders o);

    List<Orders> getOrderList(int uid);

    Orders getOrderDetailByoid(String oid);

    int updateOrderStatusByOid(String oid, int status);
}
