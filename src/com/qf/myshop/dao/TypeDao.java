package com.qf.myshop.dao;

import com.qf.myshop.entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {
    List<Type> selectAll() throws SQLException;
}
