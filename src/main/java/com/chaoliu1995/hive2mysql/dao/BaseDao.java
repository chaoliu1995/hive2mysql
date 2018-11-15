package com.chaoliu1995.hive2mysql.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/15 17:45
 */
public interface BaseDao {
    /**
     * 通用的 insert 方法
     * @param sql
     * @param resultSet
     * @throws SQLException
     */
    void insert(String sql, ResultSet resultSet) throws SQLException;
}
