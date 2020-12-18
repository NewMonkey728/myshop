package com.qf.myshop.dao.impl;

import com.qf.myshop.dao.AddresDao;
import com.qf.myshop.entity.Address;
import com.qf.myshop.entity.User;
import com.qf.myshop.tool.DruidTool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author QY
 * @date 2020-12-1115:49
 **/
public class AddresDaoImpl implements AddresDao {

    QueryRunner qr=new QueryRunner(DruidTool.ds);
    /**
     * 新增地址
     * @param a
     */
    @Override
    public void addAddress(Address a) {
        try {
            String sql="INSERT INTO address VALUES (default,?,?,?,?,?)";
             qr.update(sql,a.getUid(),a.getAname(),a.getAphone(),a.getAdetail(),a.getAstate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Address> QueryAddress(int uid) {
        try {
            String sql="SELECT aid, uid, aname, aphone, adetail, astate FROM address where uid=? ORDER BY astate DESC";
            return (List<Address>) qr.query(sql,new BeanListHandler(Address.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletAddresByaid(int aid) {
        try {
            String sql="DELETE from address WHERE aid=?";
            qr.update(sql,aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatesetDefaultByaid(int aid) {
        //因为默认地址只能有一个，所有让地址设置默认，首先把所有的都设为非默认，然后根据aid再设置默认地址
        try {
            //设置所有的默认属性为非默认
            String sql1="update address set astate=0";
            //然后根据aid设置为默认属性
            String sql2="update address set astate=1 where aid = ?";
            qr.update(sql1);
            qr.update(sql2,aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateAddressByaddress(Address a) {

        try {
            String sql="update address set aname = ? , adetail =? ,aphone=? where aid=?";
            qr.update(sql,a.getAname(),a.getAdetail(),a.getAphone(),a.getAid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
