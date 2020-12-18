package com.qf.myshop.dao;

import com.qf.myshop.entity.Cart;

import java.util.List;

public interface CartDao {
    Cart QueryBypidAndprice(int uid, Integer pid);

    void updateCartWithNumAndCount(Cart cart);

    void addCart(Cart c);

    List<Cart> QueryCartByUid(int uid);

    void deletCartByUid(String uid);

    void deletCartByCid(String cid);

    void addNum(int cid, int cnum, double ccount);

    void subNum(int cid, int cnum, double ccount);

    void deleCartByUid(int uid);
}
