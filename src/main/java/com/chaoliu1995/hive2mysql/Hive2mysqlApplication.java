package com.chaoliu1995.hive2mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hive2mysqlApplication {
    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Hive2mysqlApplication.class, args);
    }
}
