package com.demo.config;

import com.demo.entity.FieldEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    //oracle
    public static List<FieldEntity> getAllField() throws Exception {
        List<FieldEntity> list = new ArrayList<FieldEntity>();
        final String SQL = "select  a.column_name,a.data_type,b.comments " +
                "from all_tab_columns a " +
                "left outer join all_col_comments b  " +
                "on  a.column_name = b.column_name and a.table_name = b.table_name " +
                "where a.table_name=upper(?) GROUP BY a.column_name,a.data_type,b.comments";
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, PropertiesUtils.get("tableName"));
            //  pst.setInt(2, e.getAgeend());
            ResultSet rSet = pst.executeQuery();
            while (rSet.next()) {
                FieldEntity fieldEntity = new FieldEntity();
                fieldEntity.setDataBaseFieldName(rSet.getString("column_name").toUpperCase());
                fieldEntity.setComments(rSet.getString("comments"));
                fieldEntity.setFieldName(BeanHump.underlineToCamel2(rSet.getString("column_name")));
                fieldEntity.setFieldUpperName(BeanHump.underlineToCamel(rSet.getString("column_name")));
                fieldEntity.setFieldType(TypeUtils.mapType(rSet.getString("data_type")));
                list.add(fieldEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return list;
    }

    public static List<FieldEntity> getPrimary() throws Exception {
        List<FieldEntity> list = new ArrayList<FieldEntity>();
        String SQL = "select  col.constraint_name,col.table_name,column_name\n" +
                "from all_constraints con,all_cons_columns col\n" +
                "where\n" +
                "con.constraint_name=col.constraint_name and con.constraint_type='P'\n" +
                "and col.table_name= upper(?) group by col.constraint_name,col.table_name,column_name";
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, PropertiesUtils.get("tableName"));
            //  pst.setInt(2, e.getAgeend());
            ResultSet rSet = pst.executeQuery();
            while (rSet.next()) {
                FieldEntity fieldEntity = new FieldEntity();
                fieldEntity.setDataBaseFieldName(rSet.getString("column_name"));
                fieldEntity.setFieldName(BeanHump.underlineToCamel2(rSet.getString("column_name")));
                fieldEntity.setFieldUpperName(BeanHump.underlineToCamel(rSet.getString("column_name")));
                list.add(fieldEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return list;
    }

    //mysql
    public static List<FieldEntity> getAllFieldMysql() throws Exception {
        List<FieldEntity> list = new ArrayList<FieldEntity>();
        final String SQL = "SELECT\n" +
                "    COLUMN_NAME AS column_name,\n" +
                "    COLUMN_COMMENT AS comments,\n" +
                "    DATA_TYPE AS data_type\n" +
                "FROM\n" +
                "    information_schema.`COLUMNS`\n" +
                "WHERE\n" +
                "    TABLE_NAME = ? and table_schema = ?";
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, PropertiesUtils.get("tableName"));
            pst.setString(2, getDatabaseName());
            //  pst.setInt(2, e.getAgeend());
            ResultSet rSet = pst.executeQuery();
            while (rSet.next()) {
                FieldEntity fieldEntity = new FieldEntity();
                fieldEntity.setDataBaseFieldName(rSet.getString("column_name").toUpperCase());
                fieldEntity.setComments(rSet.getString("comments"));
                fieldEntity.setFieldName(BeanHump.underlineToCamel2(rSet.getString("column_name")));
                fieldEntity.setFieldUpperName(BeanHump.underlineToCamel(rSet.getString("column_name")));
                fieldEntity.setFieldType(TypeUtils.mysqlType(rSet.getString("data_type")));
                list.add(fieldEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return list;
    }

    public static List<FieldEntity> getPrimaryMysql() throws Exception {
        List<FieldEntity> list = new ArrayList<FieldEntity>();
        String SQL = "SELECT a.column_name AS column_name , b.DATA_TYPE AS data_type ,b.COLUMN_COMMENT AS comments FROM( \n" +
                "SELECT column_name AS column_name \n" +
                "  FROM INFORMATION_SCHEMA.`KEY_COLUMN_USAGE` a \n" +
                "   WHERE a.table_name = ? \n" +
                " AND a.constraint_name='PRIMARY'\n" +
                " AND table_schema=?\n" +
                " ) a LEFT JOIN \n" +
                "( SELECT * FROM information_schema.`COLUMNS` WHERE table_name = ? AND table_schema = ?  ) b\n" +
                "ON a.column_name = b.column_name";
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, PropertiesUtils.get("tableName"));
            pst.setString(2, getDatabaseName());
            pst.setString(3,PropertiesUtils.get("tableName"));
            pst.setString(4, getDatabaseName());
            //  pst.setInt(2, e.getAgeend());
            ResultSet rSet = pst.executeQuery();
            while (rSet.next()) {
                FieldEntity fieldEntity = new FieldEntity();
                fieldEntity.setDataBaseFieldName(rSet.getString("column_name"));
                fieldEntity.setFieldName(BeanHump.underlineToCamel2(rSet.getString("column_name")));
                fieldEntity.setFieldUpperName(BeanHump.underlineToCamel(rSet.getString("column_name")));
                fieldEntity.setFieldType(TypeUtils.mysqlType(rSet.getString("data_type")));
                fieldEntity.setComments(rSet.getString("comments"));
                list.add(fieldEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return list;
    }

    private static String getDatabaseName(){
        String url = PropertiesUtils.get("url");
        return url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("?"));
    }

    public static void main(String[] args){
        System.out.println(getDatabaseName());
    }


}

