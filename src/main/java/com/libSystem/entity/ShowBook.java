package com.libSystem.entity;

public class ShowBook {
    String book_id;
    String book_name;
    String book_author;
    String book_publisher;
    String type_name;
    String type_position;
    String book_date;
    int book_count;

    public ShowBook(){}

    public ShowBook(String book_id, String book_name, String book_author, String book_publisher, String type_name, String type_position, String book_date, int book_count) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.type_name = type_name;
        this.type_position = type_position;
        this.book_date = book_date;
        this.book_count = book_count;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
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

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public int getBook_count() {
        return book_count;
    }

    public void setBook_count(int book_count) {
        this.book_count = book_count;
    }

    @Override
    public String toString() {
        return "ShowBook{" +
                "book_id='" + book_id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_publisher='" + book_publisher + '\'' +
                ", type_name='" + type_name + '\'' +
                ", type_position='" + type_position + '\'' +
                ", book_date='" + book_date + '\'' +
                ", book_count=" + book_count +
                '}';
    }
}
