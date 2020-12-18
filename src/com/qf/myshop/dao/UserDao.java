package com.qf.myshop.dao;

import com.qf.myshop.entity.User;

public interface UserDao {
    User queryByusernameAndpassword(String username, String password);

    int AddUser(User u);

    User checkName(String name);

    User findUserByemail(String email);

    int activate(int isActive, int uid);
}
