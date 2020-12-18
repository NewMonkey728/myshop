package com.qf.myshop.service.impl;

import com.qf.myshop.dao.ProductDao;
import com.qf.myshop.dao.impl.ProductDaoImpl;
import com.qf.myshop.entity.PageBean;
import com.qf.myshop.entity.Product;
import com.qf.myshop.service.ProductService;
import java.util.List;

/**
 * @author QY
 * @date 2020-12-0917:54
 **/
public class ProductServiceImpl implements ProductService {
    ProductDao pd=new ProductDaoImpl();
    @Override
    public List<Product> findprolist(String tid,PageBean<Product> p) {
        //初始化分页工具信息
        p.setPageSize(8);//设置每页显示记录的条数
        p.setTotalCount(pd.QueryAllBytid(tid));//设置总记录数条数


        return p.setList(pd.QueryAll(tid,p));
    }

    @Override
    public Product QuerylistDetail(String pid) {

        return pd.QuerylistDetail(pid);
    }
}
