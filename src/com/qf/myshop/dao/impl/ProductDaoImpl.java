package com.qf.myshop.dao.impl;

import com.qf.myshop.dao.ProductDao;
import com.qf.myshop.entity.PageBean;
import com.qf.myshop.entity.Product;
import com.qf.myshop.entity.Type;
import com.qf.myshop.tool.DruidTool;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author QY
 * @date 2020-12-0917:55
 **/
public class ProductDaoImpl implements ProductDao {
    @Override
    public Integer QueryAllBytid(String tid) {
        QueryRunner qr=new QueryRunner(DruidTool.ds);
        String sql="select count(?) from product";
        int result=0;

        try {
            Number result2= (Number) qr.query(sql,new ScalarHandler(),tid);
            result=result2.intValue();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Product> QueryAll(String tid,PageBean<Product> p) {
        List<Product> list=null;
        try {
            QueryRunner qr=new QueryRunner(DruidTool.ds);
            String sql="SELECT pid,tid,pname,ptime,pimage,pprice,pstate,pinfo from product where tid=? limit ?,?;";
            list=qr.query(sql, new BeanListHandler<Product>(Product.class),tid,(p.getCurrentPage() - 1)*p.getPageSize(), p.getPageSize());

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Product QuerylistDetail(String pid) {

        try {
            QueryRunner qr=new QueryRunner(DruidTool.ds);
            String sql="select pid,p.tid,pname,ptime,pimage,pprice,pinfo,tname from product p,type t where p.tid=t.tid and pid=?";
            Map<String, Object> product= qr.query(sql,new MapHandler(),pid);
            if (product==null) {
                return null;
            }
            Product p=new Product();
            Type t=new Type();

            BeanUtils.populate(p,product);
            BeanUtils.populate(t,product);

            p.setType(t);
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
