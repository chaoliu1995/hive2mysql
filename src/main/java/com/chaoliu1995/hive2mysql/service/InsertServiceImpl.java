package com.chaoliu1995.hive2mysql.service;

import com.chaoliu1995.hive2mysql.dao.BaseDao;
import com.chaoliu1995.hive2mysql.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/15 11:26
 */
@Service("insertService")
public class InsertServiceImpl implements InsertService {

    private static Logger logger = LoggerFactory.getLogger(InsertServiceImpl.class);

    @Autowired
    private BaseDao baseDao;

    @Autowired
    private Connection hiveConnection;

    private static final String COUNT_SQL = "select count(1) from %1$S";
    /**
     * 从hive中分页查询数据的sql
     */
    private static final String SELECT_SQL = "select * from (select t.*,row_number() over (order by %4$s) rnum from %1$s as t) as tt where tt.rnum between %2$d and %3$d";

    @Override
    public void insert(String tableName,String sql,String hiveOrderByCol) throws Exception{
        if(sql == null || sql.length() < 1 || tableName == null || tableName.length() < 1){
            logger.info("---------------------missing parameter---------------------");
            return;
        }
        Statement statement = hiveConnection.createStatement();
        logger.info("---------------------select data count---------------------");
        int total = count(tableName,statement);
        logger.info("---------------------total:" + total + "---------------------");
        Pager pager = new Pager(Pager.DEFAULT_CURRENT_PAGE,Pager.DEFAULT_PAGE_SIZE,total);
        String hiveQL;
        ResultSet resultSet;
        for(int currentPage = 1;currentPage <= pager.getPageTotal(); currentPage++){
            pager = new Pager(currentPage,Pager.DEFAULT_PAGE_SIZE,total);
            hiveQL = String.format(SELECT_SQL,tableName,pager.getStartNum(),pager.getEndNum(),hiveOrderByCol);
            logger.info(hiveQL);
            resultSet = statement.executeQuery(hiveQL);
            baseDao.insert(sql,resultSet);
        }
    }

    /**
     * 查询总数
     * @param tableName
     * @return
     * @throws Exception
     */
    private int count(String tableName,Statement statement) throws Exception {
        String sql = String.format(COUNT_SQL,tableName);
        ResultSet resultSet = statement.executeQuery(sql);
        Integer total = null;
        while (resultSet.next()){
            total = resultSet.getInt(1);
        }
        return total;
    }
}
