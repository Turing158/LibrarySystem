package com.libSystem.dao;

import com.libSystem.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Resource
    JdbcTemplate jdbcTemplate;

//    查询所有用户[分页]
    @Override
    public List<User> findAll(int page) {
        String sql = "select * from user limit ?,10";
        Object[] args = {page};
        return jdbcTemplate.query(sql,args,new BeanPropertyRowMapper<>(User.class));
    }

//    查询所有用户数量
    @Override
    public int countUser() {
        String sql = "select count(*) from user";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

//  查询用户id是否存在
    @Override
    public int existUser(String id) {
        String sql = "select count(*) from user where user_id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }

//    查询指定用户id
    @Override
    public User findUser(String id) {
        String sql = "select * from user where user_id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql,args,new BeanPropertyRowMapper<>(User.class));
    }

//    插入新用户
    @Override
    public int insertUser(User user) {
        String sql = "insert into user values(?,?,?,?)";
        Object[] args = {user.getUser_id(),user.getUser_name(),user.getUser_password(),user.getUser_permission()};
        return jdbcTemplate.update(sql,args);
    }

//    删除指定用户
    @Override
    public int deleteUser(String id) {
        String sql = "delete from user where user_id=?";
        Object[] args = {id};
        return jdbcTemplate.update(sql,args);
    }

//    更改用户权限
    @Override
    public int updateUser(User user) {
        String sql = "update user set user_permission=?,user_name=?,user_password=? where user_id=?";
        Object[] args = {user.getUser_permission(),user.getUser_name(),user.getUser_password(),user.getUser_id()};
        return jdbcTemplate.update(sql,args);
    }

//    查询此用户是否有借书
    @Override
    public int existLogByUser(String user) {
        String sql = "select count(*) from book_log where user_id=?";
        Object[] args = {user};
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }


}
