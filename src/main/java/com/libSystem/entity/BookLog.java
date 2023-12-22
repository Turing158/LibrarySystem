package com.libSystem.entity;

public class BookLog {
    int log_id;
    String user_id;
    String book_id;
    String log_date;
    int log_status;
    public BookLog(){}
    public BookLog(int log_id, String user_id, String book_id, String log_date, int log_status) {
        this.log_id = log_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.log_date = log_date;
        this.log_status = log_status;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public int getLog_status() {
        return log_status;
    }

    public void setLog_status(int log_status) {
        this.log_status = log_status;
    }

    @Override
    public String toString() {
        return "BookLog{" +
                "log_id=" + log_id +
                ", user_id='" + user_id + '\'' +
                ", book_id='" + book_id + '\'' +
                ", log_date='" + log_date + '\'' +
                ", log_status='" + log_status + '\'' +
                '}';
    }
}
