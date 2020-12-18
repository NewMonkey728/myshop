package com.qf.myshop.service.impl;

import com.qf.myshop.dao.AddresDao;
import com.qf.myshop.dao.impl.AddresDaoImpl;
import com.qf.myshop.entity.Address;
import com.qf.myshop.entity.User;
import com.qf.myshop.service.AddresService;

import java.util.List;

/**
 * @author QY
 * @date 2020-12-1115:49
 **/
public class AddresServiceImpl implements AddresService {
    AddresDao ad=new AddresDaoImpl();

    @Override
    public void addAddress(Address a) {
        ad.addAddress(a);
    }

    @Override
    public List<Address> lookAddres(int uid) {
      return ad.QueryAddress(uid);
    }

    @Override
    public void deletAddres(int aid) {
        if (aid!=0){
            ad.deletAddresByaid(aid);
        }

    }

    @Override
    public void updatesetDefault(int aid) {
        if (aid!=0){
            ad.updatesetDefaultByaid(aid);
        }
    }

    @Override
    public void updateAddress(Address a) {

        if (a!=null){
            ad.updateAddressByaddress(a);
        }

    }
}
