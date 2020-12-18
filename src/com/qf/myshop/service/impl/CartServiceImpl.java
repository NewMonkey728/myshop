package com.qf.myshop.service.impl;

import com.qf.myshop.common.Const;
import com.qf.myshop.dao.CartDao;
import com.qf.myshop.dao.impl.CartDaoImpl;
import com.qf.myshop.entity.Cart;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.CartService;

import java.util.List;

/**
 * @author QY
 * @date 2020-12-1020:29
 **/
public class CartServiceImpl implements CartService {

    CartDao cd=new CartDaoImpl();
    /**
     * 加入购物车操作
     * @param uid
     * @param price
     * @param pid
     */
    @Override
    public void creatCar(int uid, Double price, Integer pid) {
        //首先判断添加购物车的商品是否存在，如果存在就加1
        //---查询方法，，以pid，用户uid查询购物车里面这条是否有值------查询操作
        Cart cart=cd.QueryBypidAndprice(uid,pid);
        if (cart!=null) {
            //如果不为空，则在该条记录数量上加1------修改操作
            //数量+1
            cart.setCnum(cart.getCnum()+1);
            //修改金额小计
            cart.setCcount(cart.getCnum()*price);
            //更新数据库
            cd.updateCartWithNumAndCount(cart);
        }else {
            //如果查询购物车该物品记录为空，则添加一条购物车信息记录-----添加操作
            Cart c=new Cart();
            c.setPid(pid);
            c.setCnum(1);
            c.setUid(uid);
            c.setCcount(price);

            cd.addCart(c);
        }



    }

    /**
     * 查看购物车操作
     * @param uid
     * @return
     */
    @Override
    public List<Cart> LookCart(int uid) {
        if (uid>0){
            return cd.QueryCartByUid(uid);
        }

        return null;
    }

    @Override
    public void clearCart(String uid) {
        if (uid!=null){

            cd.deletCartByUid(uid);
        }

    }

    @Override
    public void delCart(String cid) {

        cd.deletCartByCid(cid);
    }

    /**
     * 减商品数量
     * @param cid
     * @param cnum
     * @param price
     */
    @Override
    public void subNum(int cid, int cnum, double price) {
        //减商品数量并计算价格
        double ccount=price*cnum;


        cd.subNum(cid,cnum,ccount);
    }

    /**
     * 加商品数量
     * @param cid
     * @param cnum
     * @param price
     */
    @Override
    public void addNum(int cid, int cnum, double price) {
        //加商品数量并计算价格
        double ccount=price*cnum;


        cd.addNum(cid,cnum,ccount);
    }

    /**
     * 获得订单列表
     * @param uid
     * @return
     */
    @Override
    public List<Cart> getCartList(int uid) {
        if (uid > 0) {
            return cd.QueryCartByUid(uid);
        }
        return null;
    }

    @Override
    public void deleCart(int uid) {
        if (uid!=0){

            cd.deleCartByUid(uid);
        }
    }


}
