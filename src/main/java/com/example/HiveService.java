package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveService {

    private static final String COUNT_SQL = "SELECT COUNT(1) AS total FROM %1$S";
    /**
     * 统计数据量
     * @param statement
     * @param tableName
     * @return
     * @throws SQLException
     */
    public int count(Statement statement, String tableName) throws SQLException{
        ResultSet rs = statement.executeQuery(String.format(COUNT_SQL,tableName));
        Integer total = null;
        while (rs.next()){
            total = rs.getInt(1);
        }
        return total;
    }

    /**
     * 从hive中分页查询数据的sql
     */
    //private static final String SELECT_SQL = "select * from (select t.*,row_number() over (order by %4$s) rnum from %1$s as t) as tt where tt.rnum between %2$d and %3$d";

    public ResultSet select(Config config,Pager pager,Statement stmt) throws SQLException{
        String sql = initSQL(config,pager);
        Print.info("Hive Select SQL:");
        Print.info(sql);
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    private String initSQL(Config config,Pager pager){
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("SELECT ");
        strBuilder.append(config.getColumns());
        strBuilder.append(",rnum FROM (");

        strBuilder.append("SELECT ");
        strBuilder.append(config.getColumns());
        strBuilder.append(",row_number() over (order by ");
        strBuilder.append(config.getOrderByColumns());
        strBuilder.append(") rnum FROM ");
        strBuilder.append(config.getHiveTable());
        strBuilder.append(" AS t");
        if(!StringUtils.isEmpty(config.getHiveWhere())){
            strBuilder.append(" WHERE ");
            strBuilder.append(config.getHiveWhere());
        }

        strBuilder.append(") AS tt WHERE rnum BETWEEN ");
        strBuilder.append(pager.getStartNum());
        strBuilder.append(" AND ");
        strBuilder.append(pager.getEndNum());
        return strBuilder.toString();
    }
}
