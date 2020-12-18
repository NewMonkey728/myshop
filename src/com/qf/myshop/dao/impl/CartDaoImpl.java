package com.qf.myshop.dao.impl;

import com.qf.myshop.dao.CartDao;
import com.qf.myshop.entity.Cart;
import com.qf.myshop.entity.Product;
import com.qf.myshop.tool.DruidTool;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author QY
 * @date 2020-12-1020:29
 **/
public class CartDaoImpl implements CartDao {
    QueryRunner qr=new QueryRunner(DruidTool.ds);

    /**
     * 判断添加购物车的商品是否存在
     * @param uid
     * @param pid
     * @return
     */

    @Override
    public Cart QueryBypidAndprice(int uid, Integer pid) {
        try {
            String sql="select * from cart where uid=? and pid = ?";
            return qr.query(sql,new BeanHandler<>(Cart.class),uid,pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当同一个商品数量大于1 时根据购物车编号修改金额小计和数量
     * @param cart
     */
    @Override
    public void updateCartWithNumAndCount(Cart cart) {
        try {
            String sql="update cart set ccount=? ,cnum=? where cid = ?";
            qr.update(sql,cart.getCcount(),cart.getCnum(),cart.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * 加入购物车操作
     * @param c
     */
    @Override
    public void addCart(Cart c) {
        try {
            String sql="INSERT INTO cart VALUES (default,?,?,?,?)";
            qr.update(sql,c.getUid(),c.getPid(),c.getCcount(),c.getCnum());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看购物车操作
     * @param uid
     * @return
     */
    @Override
    public List<Cart> QueryCartByUid(int uid) {
        try {
            String sql="select cid,p.pid,p.pname,p.pprice,c.ccount,c.cnum from cart c ,product p where c.pid=p.pid and c.uid=?";
            List<Map<String, Object>> list=qr.query(sql,new MapListHandler(),uid);
            if (list==null||list.size()<1) {
                return null;
            }
            //使用循环遍历设置封装数据
            List<Cart> cList = new ArrayList<>();
            for (Map<String, Object> stringObjectMap : list) {
                //实例化需要封装数据的对象
                Product p = new Product();
                Cart c = new Cart();
                //使用beanutils封装数据
                BeanUtils.populate(p,stringObjectMap);
                BeanUtils.populate(c,stringObjectMap);
                //在购物车c中赋值p
                c.setProduct(p);
                //把封装好的数据放入集合当中
                cList.add(c);
            }
            return cList;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletCartByUid(String uid) {
        try {
            String sql="DELETE from cart WHERE uid=?";
            int i =qr.update(sql,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletCartByCid(String cid) {
        try {
            String sql="DELETE from cart WHERE cid=?";
            int i =qr.update(sql,cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addNum(int cid, int cnum, double ccount) {
        try {
            String sql="update cart set ccount=? ,cnum=? where cid = ?";
            qr.update(sql,ccount,cnum,cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subNum(int cid, int cnum, double ccount) {
        try {
            String sql="update cart set ccount=? ,cnum=? where cid = ?";
            qr.update(sql,ccount,cnum,cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleCartByUid(int uid) {
        try {
            String sql="DELETE from cart WHERE uid=?";
            int i =qr.update(sql,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
