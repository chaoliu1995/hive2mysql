package com.chaoliu1995.hive2mysql.service;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/15 11:26
 */
public interface InsertService {

    /**
     * 导入数据
     * @param tableName
     */
    void insert(String tableName) throws Exception;
}
