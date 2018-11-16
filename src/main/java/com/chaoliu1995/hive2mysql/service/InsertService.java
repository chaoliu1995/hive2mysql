package com.chaoliu1995.hive2mysql.service;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/15 11:26
 */
public interface InsertService {

    /**
     * 导出 hive 数据到 MySQL
     * @param tableName
     * @param sql
     * @param hiveOrderByCol
     * @throws Exception
     */
    void insert(String tableName,String sql,String hiveOrderByCol) throws Exception;
}
