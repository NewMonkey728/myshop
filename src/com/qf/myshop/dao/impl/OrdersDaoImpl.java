package com.qf.myshop.dao.impl;

import com.qf.myshop.dao.OrdersDao;
import com.qf.myshop.entity.Address;
import com.qf.myshop.entity.Orders;
import com.qf.myshop.tool.DruidTool;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author QY
 * @date 2020-12-110:14
 **/
public class OrdersDaoImpl implements OrdersDao {
    QueryRunner qr = new QueryRunner(DruidTool.ds);

    @Override
    public int insertOrders(Orders o) {
        try {
            String sql="insert into orders values(?,?,?,?,?,?)";
            return qr.update(sql,o.getOid(),o.getUid(),o.getAid(),o.getOcount(),o.getOtime(),o.getOstate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Orders> getOrderList(int uid) {
        try {
            String sql="select `oid`,o.`uid`,a.`aid`,`ocount`,`otime`,`ostate`,a.adetail from orders o ,address a where a.aid=o.aid and o.uid=?";
            List<Map<String, Object>> query = qr.query(sql,new MapListHandler(), uid);
            //判断
            if (query==null||query.size()<1) {
                return null;
            }
            List<Orders> list = new ArrayList<>();
            //遍历
            for (Map<String, Object> stringObjectMap : query) {
                //创建对象
                Address a = new Address();
                Orders o = new Orders();
                //使用beanutils封装

                BeanUtils.populate(a, stringObjectMap);
                BeanUtils.populate(o, stringObjectMap);
                o.setAddress(a);
                list.add(o);
            }
            return list;
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
    public Orders getOrderDetailByoid(String oid) {
        //order和addres量表查询  oid，otime,ocount,uid,aid,aphone,ostate,adetail,aname
        try {
        String sql = "select `oid`,o.`uid`,a.`aid`,`ocount`,`otime`,`ostate`,a.adetail ,a.aphone,a.aname " +
                "from orders o ,address a where a.aid=o.aid and o.oid=?";
        Map<String, Object> query = qr.query(sql, new MapHandler(), oid);
        //判断查询的是否为空
            if (query==null||query.size()<0) {
                return null;
            }

            //创建需要的实体类
            Orders o = new Orders();
            Address a=new Address();
            //调用封装类，将数据进行封装
            BeanUtils.populate(a,query);
            BeanUtils.populate(o,query);
            o.setAddress(a);
            //然后返回orders
            return o;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateOrderStatusByOid(String oid, int status) {
        try {
            return qr.update("update orders set ostate = ? where oid = ?",status,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
