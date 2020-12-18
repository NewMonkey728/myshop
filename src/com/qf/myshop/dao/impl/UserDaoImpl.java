package com.qf.myshop.dao.impl;

import com.qf.myshop.dao.UserDao;
import com.qf.myshop.entity.User;
import com.qf.myshop.tool.DruidTool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    /**
     * 登录根据账号和密码查询操作
     * @param username
     * @param password
     * @return
     */
    @Override
    public User queryByusernameAndpassword(String username, String password) {
        try {
            QueryRunner qr= new QueryRunner(DruidTool.ds);
            String sql="select uid,username,upassword from user where username=? and upassword=?";
            return qr.query(sql,new BeanHandler<>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 注册添加用户操作
     * @param
     * @return
     */
    @Override
    public int AddUser(User u) {
        try {
            QueryRunner qr = new QueryRunner(DruidTool.ds);
            String sql = "INSERT INTO user (uid,username,upassword,email,usex,ustatus,code,urole) VALUES ( default,?,?,?,?,?,?,?)";
            return qr.update(sql,
                    u.getUsername(), u.getUpassword(), u.getEmail(),
                    u.getUsex(), u.getUstatus(), u.getCode(), u.getUrole());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据用户名查询是否又该用户存在
     * @param name
     * @return
     */
    @Override
    public User checkName(String name) {
        User user=null;
        try {

            QueryRunner qr = new QueryRunner(DruidTool.ds);
            user= qr.query("select uid,username from user where username = ?",
                    new BeanHandler<>(User.class), name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据用户邮箱查询
     * @param email
     * @return
     */
    @Override
    public User findUserByemail(String email) {

        try {
            QueryRunner qr=new QueryRunner(DruidTool.ds);
         return  qr.query("select uid,username,upassword,email,usex,ustatus,code,urole from user where email = ?",
                 new BeanHandler<>(User.class),email);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 激活用户操作
     * @param isActive
     * @param uid
     * @return
     */
    @Override
    public int activate(int isActive, int uid) {
       try {
           QueryRunner qr=new QueryRunner(DruidTool.ds);
           return qr.update("update user set ustatus = ? where uid = ?",isActive,uid);
       }catch (SQLException e) {
           e.printStackTrace();
    }
        return 0;
    }
}
