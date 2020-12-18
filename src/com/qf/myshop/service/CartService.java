package com.qf.myshop.service;

import com.qf.myshop.entity.Cart;

import java.util.List;

public interface CartService {
    void creatCar(int uid, Double price, Integer pid);

    List<Cart> LookCart(int uid);

    void clearCart(String uid);

    void delCart(String cid);

    void subNum(int cid, int cnum, double price);

    void addNum(int cid, int cnum, double price);

    List<Cart> getCartList(int uid);

    void deleCart(int uid);
}
