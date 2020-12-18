package com.qf.myshop.service;

import com.qf.myshop.entity.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> getOrderList(int uid);

    void createOrder(int uid, int aid);

    Orders getOrderDetaiByoid(String oid);

    int updateOrderStatusByOid(String oid, int code);
}
