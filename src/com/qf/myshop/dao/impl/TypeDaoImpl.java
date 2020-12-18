package com.qf.myshop.dao.impl;

import com.qf.myshop.dao.TypeDao;
import com.qf.myshop.entity.Type;
import com.qf.myshop.tool.DruidTool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QY
 * @date 2020-12-0917:29
 **/
public class TypeDaoImpl implements TypeDao {
    @Override
    public List<Type> selectAll() throws SQLException {

        QueryRunner queryRunner = new QueryRunner(DruidTool.ds);

        String sql = "select tid,tname ,tinfo from type";

        List<Type> list = queryRunner.query(sql, new BeanListHandler<Type>(Type.class));
        return list;
    }
}
