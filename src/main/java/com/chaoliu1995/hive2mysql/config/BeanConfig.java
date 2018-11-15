package com.chaoliu1995.hive2mysql.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/15 10:11
 */
@Configuration
public class BeanConfig {

    private Logger logger = LoggerFactory.getLogger(BeanConfig.class);

    @Value("${hive.url}")
    private String hiveUrl;

    @Bean("hiveConnection")
    public Connection connection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(hiveUrl, "", "");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("hive Connection 初始化失败");
            System.exit(1);
        }
        return connection;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
