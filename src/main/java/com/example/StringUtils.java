package com.example;

/**
 * @Description:
 * @Date: 2021/3/30 15:08
 */
public class StringUtils {

    private StringUtils(){}

    /**
     * 校验字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str == null || str.trim().length() == 0){
            return true;
        }else{
            return false;
        }
    }

}
