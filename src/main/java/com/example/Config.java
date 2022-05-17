package com.example;

public class Config {

    private String hiveConnect;
    private String hiveUsername;
    private String hivePassword;
    private String hiveTable;
    private String hiveWhere;
    private String mysqlConnect;
    private String mysqlUsername;
    private String mysqlPassword;
    private String mysqlTable;
    private String columns;
    private String orderByColumns;
    private Integer rows;

    public String getHiveConnect() {
        return hiveConnect;
    }

    public void setHiveConnect(String hiveConnect) {
        this.hiveConnect = hiveConnect;
    }

    public String getHiveUsername() {
        return hiveUsername;
    }

    public void setHiveUsername(String hiveUsername) {
        this.hiveUsername = hiveUsername;
    }

    public String getHivePassword() {
        return hivePassword;
    }

    public void setHivePassword(String hivePassword) {
        this.hivePassword = hivePassword;
    }

    public String getHiveTable() {
        return hiveTable;
    }

    public void setHiveTable(String hiveTable) {
        this.hiveTable = hiveTable;
    }

    public String getMysqlConnect() {
        return mysqlConnect;
    }

    public void setMysqlConnect(String mysqlConnect) {
        this.mysqlConnect = mysqlConnect;
    }

    public String getMysqlUsername() {
        return mysqlUsername;
    }

    public void setMysqlUsername(String mysqlUsername) {
        this.mysqlUsername = mysqlUsername;
    }

    public String getMysqlPassword() {
        return mysqlPassword;
    }

    public void setMysqlPassword(String mysqlPassword) {
        this.mysqlPassword = mysqlPassword;
    }

    public String getMysqlTable() {
        return mysqlTable;
    }

    public void setMysqlTable(String mysqlTable) {
        this.mysqlTable = mysqlTable;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getOrderByColumns() {
        return orderByColumns;
    }

    public void setOrderByColumns(String orderByColumns) {
        this.orderByColumns = orderByColumns;
    }

    public String getHiveWhere() {
        return hiveWhere;
    }

    public void setHiveWhere(String hiveWhere) {
        this.hiveWhere = hiveWhere;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
