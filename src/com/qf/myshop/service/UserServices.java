package com.qf.myshop.service;

import com.qf.myshop.entity.User;

public interface UserServices {

    User login(String username, String password);

    int register(User u);

    User checkName(String name);

    int activate(String email, String code);
}
