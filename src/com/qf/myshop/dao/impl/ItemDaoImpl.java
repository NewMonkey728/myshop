package com.qf.myshop.dao.impl;

import com.qf.myshop.dao.ItemDao;
import com.qf.myshop.entity.Item;
import com.qf.myshop.entity.Product;
import com.qf.myshop.tool.DruidTool;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author QY
 * @date 2020-12-120:59
 **/
public class ItemDaoImpl implements ItemDao {

    QueryRunner qr = new QueryRunner(DruidTool.ds);
    @Override
    public void addItems(List<Item> items) {
        try {


            Object[][] obj = new Object[items.size()][];
            //遍历设置数据
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                obj[i] = new Object[]{item.getOid(), item.getPid(), item.getIcount(), item.getInum()};

            }

            qr.batch("insert into item values (default,?,?,?,?)", obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> getItemDetailByoid(String oid) {
        //Item和Product两表查询，pname,ptime,pimage,pprice,pstate，icount,inum
        //因为要查询的ItemItem和Product是多对一的关系，所以查询的Item是集合类型
        try {
            String sql="select p.pname,p.ptime,p.pimage,p.pprice,p.pstate,i.icount,i.inum from item i,product p where i.pid=p.pid and i.oid=?";
           List<Map<String, Object>> query=qr.query(sql,new MapListHandler(),oid);

           //首先创建一个item集合
            List<Item> items=new ArrayList<>();
            //遍历
            for (Map<String, Object> maplist : query) {
                //创建封装数据的对象
                Item i=new Item();
                Product p=new Product();
                //调用封装类
                BeanUtils.populate(p,maplist);
                BeanUtils.populate(i,maplist);
                i.setProduct(p);
                items.add(i);
            }
            return items;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
