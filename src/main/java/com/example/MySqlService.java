package com.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MySqlService {

    public void insert(PreparedStatement ps, ResultSet rs,int num) throws SQLException {
        Print.info("MySQL Insert Start");
        while (rs.next()) {
            for(int i = 1; i <= num; i++){
                ps.setObject(i,rs.getObject(i));
            }
            try{
                ps.execute();
            }catch (Exception e){
                e.printStackTrace();
                Print.info(e.getMessage());
                Print.info("异常数据：" + resultSetToString(rs));
            }
        }
        Print.info("MySQL Insert End");
    }

    /**
     * 拼接数据
     * @param rs
     * @return
     * @throws SQLException
     */
    private String resultSetToString(ResultSet rs) throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder();
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){
            sqlBuilder.append(rs.getString(i));
            sqlBuilder.append("\t");
        }
        return sqlBuilder.toString();
    }
}
