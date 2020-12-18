package com.qf.myshop.service;

import com.qf.myshop.entity.PageBean;
import com.qf.myshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findprolist(String tid,PageBean<Product> p);

    Product QuerylistDetail(String pid);
}
