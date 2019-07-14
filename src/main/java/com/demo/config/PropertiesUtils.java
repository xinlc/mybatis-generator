package com.demo.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {

    private static final String dbconfig = "config.properties";
    private static Properties prop = new Properties();

    static{
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(dbconfig);
            prop.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key){
        return prop.getProperty(key);
    }

    public static List<String> getConfigKey(){
        List<String> list = new ArrayList<String>();
        for(Map.Entry entry:prop.entrySet()){
            if(entry.getKey().toString().startsWith("config.")){
                list.add(entry.getKey().toString().replace("config.",""));
            }
        }
        return list;
    }
}
