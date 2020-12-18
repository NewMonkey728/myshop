package com.qf.myshop.service.impl;

import com.qf.myshop.dao.TypeDao;
import com.qf.myshop.dao.impl.TypeDaoImpl;
import com.qf.myshop.entity.Type;
import com.qf.myshop.service.TypeService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author QY
 * @date 2020-12-0917:21
 **/
public class TypeServiceImpl implements TypeService {
    @Override
    public List<Type> findAll() throws SQLException {
        TypeDao typeDao = new TypeDaoImpl();

        List<Type> types = typeDao.selectAll();

        return types;
    }
}
