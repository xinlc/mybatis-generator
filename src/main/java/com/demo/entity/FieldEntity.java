package com.demo.entity;

public class FieldEntity {

    private String dataBaseFieldName;
    private String fieldName;
    private String fieldUpperName;
    private String fieldType;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDataBaseFieldName() {
        return dataBaseFieldName;
    }

    public void setDataBaseFieldName(String dataBaseFieldName) {
        this.dataBaseFieldName = dataBaseFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldUpperName() {
        return fieldUpperName;
    }

    public void setFieldUpperName(String fieldUpperName) {
        this.fieldUpperName = fieldUpperName;
    }

    @Override
    public String toString() {
        return "FieldEntity{" +
                "dataBaseFieldName='" + dataBaseFieldName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldUpperName='" + fieldUpperName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
