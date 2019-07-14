package com.demo.config;

public class TypeUtils {

    public static String mapType(String type){
        String dataType = type.toLowerCase();
        switch (dataType){
            case "varchar":
                return "String";
            case "char":
                return "String";
            case "number":
                return "BigDecimal";
            case "integer":
                return "Integer";
            case "float":
                return "Bigdecimal";
            case "date":
                return "Date";
            case "long":
                return "Long";
            case "timestamp":
                return "Date";
            default:
                return "String";
        }
    }

    public static String mysqlType(String type){
        String dataType = type.toLowerCase();
        switch (dataType){
            case "varchar":
                return "String";
            case "char":
                return "String";
            case "bigint":
                return "Long";
            case "float":
                return "BigDecimal";
            case "double":
                return "BigDecimal";
            case "decimal":
                return "BigDecimal";
            case "date":
                return "Date";
            case "int":
                return "Integer";
            case "datetime":
                return "Date";
            default:
                return "String";
        }
    }
}
