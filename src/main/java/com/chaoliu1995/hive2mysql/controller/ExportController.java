package com.chaoliu1995.hive2mysql.controller;

import com.chaoliu1995.hive2mysql.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/11/15 14:05
 */
@Controller
public class ExportController {

    @Autowired
    private InsertService insertService;

    @RequestMapping("/export")
    @ResponseBody
    public String export(@RequestParam("tableName")String tableName){
        try{
            insertService.insert(tableName);
        }catch (Exception e){
            e.printStackTrace();
            return "error:"+e.getMessage();
        }
        return "success";
    }
}
