package com.demo.config;

import com.demo.entity.FieldEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.util.*;


public class FileDataUtils {

    public static void generatorFile()throws Exception{
        String base = PropertiesUtils.get("base");
        List<String> configKey = PropertiesUtils.getConfigKey();
        Map<String,Object> map = new HashMap<>(16);
        List<FieldEntity> fieldEntities = new ArrayList<>();
        List<FieldEntity> primaries = new ArrayList<>();
        if("oracle".equals(base)){
            fieldEntities = DataUtils.getAllField();
            primaries = DataUtils.getPrimary();
        }else{
            fieldEntities = DataUtils.getAllFieldMysql();
            primaries = DataUtils.getPrimaryMysql();
        }
        List<FieldEntity> fields = new ArrayList<>();
        Boolean isDate = false;
        Boolean isNumber = false;
        for(FieldEntity fieldEntity : fieldEntities){
            if(fieldEntity.getFieldType().equals("Date")){
                isDate = true;
            }
            if(fieldEntity.getFieldType().equals("BigDecimal")){
                isNumber = true;
            }
            if(!containsPrimary(fieldEntity,primaries)){
                fields.add(fieldEntity);
            }
        }

        String domainObjectName = PropertiesUtils.get("domainObjectName");

        map.put("isDate",isDate);
        map.put("isNumber",isNumber);
        map.put("className",domainObjectName);
        map.put("classLowerName",domainObjectName.substring(0,1).toLowerCase()+domainObjectName.substring(1));
        map.put("fieldEntities",fieldEntities);
        map.put("primaries",primaries);
        map.put("database",PropertiesUtils.get("database"));
        map.put("tableName",PropertiesUtils.get("tableName"));
        map.put("fields",fields);

        for(String key : configKey){
            Configuration config = new Configuration();

            config.setDefaultEncoding("UTF-8");
            config.setTemplateUpdateDelay(0);
            config.setDirectoryForTemplateLoading(new File(PropertiesUtils.get("filePath")));

            Template template = config.getTemplate("ftl/"+key+".ftl");
            template.process(map,new FileWriter(changeString(PropertiesUtils.get("config."+key))));
        }
        System.out.println("自动生成成功");
    }

    private static Boolean containsPrimary(FieldEntity fieldEntity,List<FieldEntity> primaries){
        for(FieldEntity fe : primaries){
            if(fe.getDataBaseFieldName().toLowerCase().equals(fieldEntity.getDataBaseFieldName().toLowerCase())){
               return true;
            }
        }
        return false;
    }

    private static String changeString(String str){
        int begin=-1;
        int end = -1;
        String el= "";
        Set<String> els = new HashSet<String>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '{'){
                begin=i;
                continue;
            }
            if(str.charAt(i) == '}'){
                if(begin!=-1){
                    end=i;
                    continue;
                }
            }
            if(end!=-1){
                els.add(el);
                begin=-1;
                end=-1;
                el="";
            }
            if(begin!=-1){
                el+=str.charAt(i);
            }
        }
       // System.out.println(els);
        for(String e:els){
        //    System.out.println(e);
            str = str.replace("{"+e+"}",PropertiesUtils.get(e));
        }
        return str;
    }

}
