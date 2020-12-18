package com.qf.myshop.service;
import com.qf.myshop.entity.Address;
import java.util.List;

public interface AddresService {

    void addAddress(Address a);

    List<Address> lookAddres(int uid);

    void deletAddres(int aid);

    void updatesetDefault(int aid);

    void updateAddress(Address a);
}
