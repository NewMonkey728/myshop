package com.qf.myshop.tool;


import com.qf.myshop.entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        QueryRunner qr=new QueryRunner(DruidTool.ds);
        String sql="SELECT * from product";
        List<Product> list=qr.query(sql,new BeanListHandler<Product>(Product.class));
        for (Product s:list) {
            System.out.printf(String.valueOf(s)+"\n");
        }
    }
}
