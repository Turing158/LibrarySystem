package com.libSystem.entity;

public class BookType {
    String type_id;
    String type_name;
    String type_position;
    public BookType(){}
    public BookType(String type_id, String type_name, String type_position) {
        this.type_id = type_id;
        this.type_name = type_name;
        this.type_position = type_position;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_position() {
        return type_position;
    }

    public void setType_position(String type_position) {
        this.type_position = type_position;
    }
}
