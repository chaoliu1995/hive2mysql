package com.chaoliu1995.hive2mysql;

import com.chaoliu1995.hive2mysql.service.InsertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Hive2mysqlApplication {

    private static Logger logger = LoggerFactory.getLogger(Hive2mysqlApplication.class);

    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Hive2mysqlApplication.class, args);
        InsertService insertService = (InsertService) ctx.getBean("insertService");
        if(args != null && args.length != 3){
            logger.info("---------------------missing parameter---------------------");
            System.exit(1);
        }
        logger.info("---------------------export-start---------------------");
        logger.info("---------------------tableName:" + args[0] + "---------------------");
        logger.info("---------------------insertSQL:" + args[1] + "---------------------");
        logger.info("---------------------hiveOrderByCol:" + args[2] + "---------------------");
        try{
            insertService.insert(args[0],args[1],args[2]);
            logger.info("---------------------export-end");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
