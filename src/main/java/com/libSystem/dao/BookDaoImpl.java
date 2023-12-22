package com.libSystem.dao;

import com.libSystem.entity.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class BookDaoImpl implements BookDao{
    @Resource
    JdbcTemplate jdbcTemplate;

//    查找所有书[有分页]
    @Override
    public List<ShowBook> findAll(int page) {
        String sql = "select book_id,book_name,book_author,book_publisher,book_date,book_count,type_name,type_position from book join book_type on book.book_type = book_type.type_id limit ?,10";
        Object[] args = {page};
        return jdbcTemplate.query(sql,args,new BeanPropertyRowMapper<>(ShowBook.class));
    }

//    查询所有书的数量
    @Override
    public int countAll() {
        String sql = "select count(*) from book";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

//  通过书号查询书
    @Override
    public Book findBookById(String id) {
        String sql = "select * from book where book_id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql,args,new BeanPropertyRowMapper<>(Book.class));
    }
//  该书号是否存在
    @Override
    public int existBook(String id) {
        String sql = "select count(*) from book where book_id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }
//  通过名字查询书[有分页]
    @Override
    public ShowBook findBook(String name,int page) {
        String sql = "select book_id,book_name,book_author,book_publisher,book_date,book_count,type_name,type_position from book join book_type on book.book_type = book_type.type_id where book_name like '%' ? '%' limit ?,10";
        Object[] args = {name,page};
        return jdbcTemplate.queryForObject(sql,args,new BeanPropertyRowMapper<>(ShowBook.class));
    }

//    查询该用户是否有借
    @Override
    public int existBookLog(String bookId,String user) {
        String sql = "select count(*) from book_log where book_id=? and user_id=?";
        Object[] args = {bookId,user};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }

//    查找用户借了什么书
    @Override
    public List<UserBook> findUserBook(String user, int page) {
        String sql = "select book.book_id,book_name,book_author,book_publisher,type_name,type_position,log_date from book_log join book on book_log.book_id=book.book_id join book_type on book.book_type=book_type.type_id where user_id = ? limit ?,10";
        Object[] args = {user,page};
        return jdbcTemplate.query(sql,args,new BeanPropertyRowMapper<>(UserBook.class));
    }
//  查找用户借了多少书
    @Override
    public int countUserBook(String user_id) {
        String sql = "select count(*) from book_log where user_id=?";
        Object[] args = {user_id};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }

//  删除书
    @Override
    public int deleteBook(String id) {
        String sql = "delete from book where book_id=?";
        Object[] args = {id};
        return jdbcTemplate.update(sql,args);
    }

//    添加书
    @Override
    public int insertBook(Book book) {
        String sql = "insert into book values(?,?,?,?,?,?,?)";
        Object[] args = {book.getBook_id(),book.getBook_name(),book.getBook_author(),book.getBook_publisher(),book.getBook_date(),book.getBook_type(),book.getBook_count()};
        jdbcTemplate.update(sql,args);
        return 0;
    }

//    更新书
    @Override
    public int updateBook(Book book) {
        String sql = "update book set book_name=?,book_author=?,book_publisher=?,book_type=?,book_date=?,book_count=? where book_id = ?";
        Object[] args = {book.getBook_name(),book.getBook_author(),book.getBook_publisher(),book.getBook_type(),book.getBook_date(),book.getBook_count(),book.getBook_id()};
        return jdbcTemplate.update(sql,args);
    }

//    更新书数量
    @Override
    public int updateBookCount(String book_id, int count) {
        String sql = "update book set book_count=? where book_id = ?";
        Object[] args = {count,book_id};
        return jdbcTemplate.update(sql,args);
    }


    //  借书
    @Override
    public int borrowBook(BookLog bookLog) {
        String sql = "insert into book_log (user_id,book_id,log_date,log_status) values(?,?,?,?)";
        Object[] args = {bookLog.getUser_id(),bookLog.getBook_id(),bookLog.getLog_date(),bookLog.getLog_status()};
        return jdbcTemplate.update(sql,args);
    }
//  还书
    @Override
    public int backBook(String book_id, String user_id, String date) {
        String sql = "delete from book_log where book_id=? and user_id=? and log_date=?";
        Object[] args = {book_id,user_id,date};
        return jdbcTemplate.update(sql,args);
    }

//  获取书类型
    @Override
    public List<BookType> findAllBookType() {
        String sql = "select * from book_type";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(BookType.class));
    }

//    查询此书id是否有被借
    @Override
    public int existLogByBook(String book_id) {
        String sql = "select count(*) from book_log where book_id=?";
        Object[] args = {book_id};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }


}
