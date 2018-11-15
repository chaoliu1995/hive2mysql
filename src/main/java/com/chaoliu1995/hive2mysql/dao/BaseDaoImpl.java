package com.chaoliu1995.hive2mysql.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/15 17:08
 */
@Component("baseDao")
public class BaseDaoImpl implements BaseDao {

    private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 拼接数据
     * @param resultSetMetaData
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private String resultSetToString(ResultSetMetaData resultSetMetaData,ResultSet resultSet) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder();
        for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
            sqlBuilder.append(resultSet.getString(i));
            sqlBuilder.append("\t");
        }
        return sqlBuilder.toString();
    }

    @Override
    public void insert(String sql,ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData;
        while (resultSet.next()) {
            resultSetMetaData = resultSet.getMetaData();
            //此处减1，是因为 hive 的查询结果中，最后一个字段是 row_number
            Object[] objects = new Object[resultSetMetaData.getColumnCount()-1];
            for(int i = 0; i < resultSetMetaData.getColumnCount()-1; i++){
                objects[i] = resultSet.getString(i+1);
            }
            try{
                jdbcTemplate.update(sql,objects);
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
                logger.error("异常数据：" + resultSetToString(resultSetMetaData,resultSet));
            }
        }
    }

}
