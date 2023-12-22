package com.libSystem.dao;

import com.libSystem.entity.*;

import java.util.List;

public interface BookDao {
    List<ShowBook> findAll(int page);
    int countAll();
    Book findBookById(String id);
    int existBook(String id);
    int deleteBook(String id);
    int insertBook(Book book);
    int updateBook(Book book);
    int updateBookCount(String book_id,int count);
    ShowBook findBook(String name,int page);
    int existBookLog(String bookId,String date);
    List<UserBook> findUserBook(String user, int page);
    int countUserBook(String user_id);
    int borrowBook(BookLog bookLog);
    int backBook(String book_id,String user_id,String date);
    List<BookType> findAllBookType();
    int existLogByBook(String book_id);
}
