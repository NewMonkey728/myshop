package com.qf.myshop.service.impl;

import com.qf.myshop.common.Const;
import com.qf.myshop.dao.UserDao;
import com.qf.myshop.dao.impl.UserDaoImpl;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.UserServices;
import com.qf.myshop.tool.Base64Utils;
import com.qf.myshop.tool.EmailUtils;

import java.awt.peer.ButtonPeer;

public class UserServicesImpl implements UserServices {
    UserDao ud=new UserDaoImpl();

    /**
     * 登录业务
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        return ud.queryByusernameAndpassword(username,password);
    }

    /**
     * 注册业务
     * @param u
     * @return
     */
    @Override
    public int register(User u) {
        //首先判断u是否为空
        if (u!=null) {
            int i = ud.AddUser(u);
            if (i>0){
                //发送邮件
                EmailUtils.sendEmail(u);
            }
            return i;
        }
        return 0;
    }

    /**
     * 验证用户是否存在
     * @param name
     * @return
     */
    @Override
    public User checkName(String name) {
        return ud.checkName(name);
    }

    /**
     *激活账户
     * @param email
     * @param code
     * @return
     */
    //返回值如果为1 激活 0激活失败 2没有账号 3该用户已经激活
    @Override
    public int activate(String email, String code) {

        //1,首先查询该用户是否存在
        //查询之前先把email和code解码一下
        email = Base64Utils.decode(email);
        code = Base64Utils.decode(code);
        System.out.println("*****"+code);
        User u = ud.findUserByemail(email);
        System.out.println(u);
        if (u==null){
            return 2;
        }
        //2,判断该用户已被激活
        if (u.getUstatus()== Const.UserConst.IS_ACTIVE){
            //激活了就直接返回3
            return 3;
        }

        //3,如果没有激活才调用业务层去激活方法
        if (u.getCode().equals(code)){
            //调用业务层激活用户

            return ud.activate(Const.UserConst.IS_ACTIVE,u.getUid());
        }
        return 0;
    }
}
