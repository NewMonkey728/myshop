package com.qf.myshop.dao;

import com.qf.myshop.entity.Item;

import java.util.List;

public interface ItemDao {

    void addItems(List<Item> items);

    List<Item> getItemDetailByoid(String oid);
}
