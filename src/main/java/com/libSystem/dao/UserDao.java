package com.libSystem.dao;

import com.libSystem.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll(int page);
    int countUser();
    int existUser(String id);
    User findUser(String id);
    int insertUser(User user);
    int deleteUser(String id);
    int updateUser(User user);
    int existLogByUser(String user);
}
