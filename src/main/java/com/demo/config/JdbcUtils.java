package com.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils {


    public static Connection getConnection(){
        try {
            Class.forName(PropertiesUtils.get("driverClassName"));
            return DriverManager.getConnection(PropertiesUtils.get("url"),PropertiesUtils.get("username"),PropertiesUtils.get("password"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        System.out.print(getConnection());
    }
}