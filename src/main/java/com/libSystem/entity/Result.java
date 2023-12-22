package com.libSystem.entity;

public class Result {
    String status;
    Object object;
    public Result(){}
    public Result(String status, Object object) {
        this.status = status;
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", object=" + object +
                '}';
    }
}
