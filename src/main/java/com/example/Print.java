package com.example;

public class Print {
    public static void info(Object obj){
        System.out.print("-----INFO-----");
        System.out.println(obj);
    }

    public static void error(Object obj){
        System.out.print("-----ERROR-----");
        System.out.println(obj);
    }
}
