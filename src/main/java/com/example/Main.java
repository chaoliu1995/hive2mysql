package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws Exception {

        Print.info("----------hive2mysql start----------");

        //Config config = readConfig("C:\\Users\\lenovo\\Desktop\\region.properties");

        if(args.length < 1){
            Print.info("请输入配置文件路径");
            System.exit(1);
        }
        Config config = readConfig(args[0]);
        checkConfig(config);

        String insertSQL = initSQL(config);
        Print.info("MySQL Insert SQL:");
        Print.info(insertSQL);

        Connection hiveConn = null;
        Connection mysqlConn = null;
        Statement hiveStmt = null;
        PreparedStatement mysqlStmt = null;
        try{
            hiveConn = DriverManager.getConnection(
                    config.getHiveConnect(),
                    config.getHiveUsername(),
                    config.getHivePassword());
            hiveStmt = hiveConn.createStatement();

            mysqlConn = DriverManager.getConnection(
                    config.getMysqlConnect(),
                    config.getMysqlUsername(),
                    config.getMysqlPassword());
            mysqlStmt = mysqlConn.prepareStatement(insertSQL);

        }catch (Exception e){
            Print.error("Database Connection init failed");
            e.printStackTrace();
            if(hiveConn != null){
                hiveConn.close();
            }
            if(hiveStmt != null){
                hiveStmt.close();
            }
            if(mysqlConn != null){
                mysqlConn.close();
            }
            if(mysqlStmt != null){
                mysqlStmt.close();
            }
            return;
        }

        int num = config.getColumns().split(",").length;

        HiveService hiveService = new HiveService();
        MySqlService mySqlService = new MySqlService();
        try {
            int total = hiveService.count(hiveStmt,config.getHiveTable());
            Print.info("total = "+total);
            ResultSet rs;
            Pager pager = new Pager(Pager.DEFAULT_CURRENT_PAGE,config.getRows(),total);
            for(int currentPage = 1;currentPage <= pager.getPageTotal(); currentPage++){
                pager = new Pager(currentPage,config.getRows(),total);
                rs = hiveService.select(config,pager,hiveStmt);
                mySqlService.insert(mysqlStmt,rs,num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            hiveStmt.close();
            mysqlStmt.close();
            hiveConn.close();
            mysqlConn.close();
            Print.info("----------hive2mysql end----------");
        }
    }

    /**
     * 读取配置文件
     * @param path
     * @return
     * @throws IOException
     */
    private static Config readConfig(String path) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(path);
        properties.load(fis);
        Config config = new Config();
        config.setHiveConnect(properties.getProperty("hiveConnect"));
        config.setHiveUsername(properties.getProperty("hiveUsername"));
        config.setHivePassword(properties.getProperty("hivePassword"));
        config.setHiveTable(properties.getProperty("hiveTable"));
        config.setHiveWhere(properties.getProperty("hiveWhere"));
        config.setMysqlConnect(properties.getProperty("mysqlConnect"));
        config.setMysqlUsername(properties.getProperty("mysqlUsername"));
        config.setMysqlPassword(properties.getProperty("mysqlPassword"));
        config.setMysqlTable(properties.getProperty("mysqlTable"));
        config.setColumns(properties.getProperty("columns"));
        config.setOrderByColumns(properties.getProperty("orderByColumns"));
        config.setRows(Integer.valueOf(properties.getProperty("rows")));
        return config;
    }

    /**
     * 检查配置项是否有空
     * @param config
     */
    private static void checkConfig(Config config) {
        if(StringUtils.isEmpty(config.getHiveConnect())
                || StringUtils.isEmpty(config.getHiveConnect())
                || StringUtils.isEmpty(config.getHiveTable())
                || StringUtils.isEmpty(config.getMysqlConnect())
                || StringUtils.isEmpty(config.getMysqlUsername())
                || StringUtils.isEmpty(config.getMysqlPassword())
                || StringUtils.isEmpty(config.getMysqlTable())
                || StringUtils.isEmpty(config.getColumns())
                || StringUtils.isEmpty(config.getOrderByColumns())
                || config.getRows() == null){
            Print.info("配置文件中参数不完整");
            System.exit(1);
        }
    }

    /**
     * 拼接MySQL Insert SQL
     * @param config
     * @return
     */
    private static String initSQL(Config config){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("INSERT INTO ");
        strBuilder.append(config.getMysqlTable());
        strBuilder.append("(");
        strBuilder.append(config.getColumns());
        strBuilder.append(") VALUES(");

        String[] arr = config.getColumns().split(",");
        for(int i = 0; i < arr.length; i++){
            if(i < arr.length - 1){
                strBuilder.append("?,");
            }else{
                strBuilder.append("?)");
            }
        }
        return strBuilder.toString();
    }

}
