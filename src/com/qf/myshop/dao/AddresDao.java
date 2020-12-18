package com.qf.myshop.dao;

import com.qf.myshop.entity.Address;
import com.qf.myshop.entity.User;

import java.util.List;

public interface AddresDao {
    void addAddress(Address a);

    List<Address> QueryAddress(int uid);

    void deletAddresByaid(int aid);

    void updatesetDefaultByaid(int aid);

    void updateAddressByaddress(Address a);
}
