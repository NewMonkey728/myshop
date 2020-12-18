package com.qf.myshop.dao;

import com.qf.myshop.entity.PageBean;
import com.qf.myshop.entity.Product;

import java.util.List;

public interface ProductDao {
    Integer QueryAllBytid(String tid);

    List<Product> QueryAll(String tid,PageBean<Product> p);

    Product QuerylistDetail(String pid);
}
