package com.libSystem.entity;

public class User {
    String user_id;
    String user_name;
    String user_password;
    int user_permission;
    public User(){}

    public User(String user_id, String user_name, String user_password, int user_permission) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_permission = user_permission;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_permission() {
        return user_permission;
    }

    public void setUser_permission(int user_permission) {
        this.user_permission = user_permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_permission=" + user_permission +
                '}';
    }
}
