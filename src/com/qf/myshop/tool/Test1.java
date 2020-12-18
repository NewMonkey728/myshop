package com.qf.myshop.tool;

import com.qf.myshop.entity.User;
import sun.applet.Main;

/**
 * @author QY
 * @date 2020-12-0911:50
 **/
public class Test1 {
    public static void main(String[] args) {
        User u=new User("name","123","男","467584306@qq.com",0,"123456",0);
        EmailUtils.sendEmail(u);
    }

}
