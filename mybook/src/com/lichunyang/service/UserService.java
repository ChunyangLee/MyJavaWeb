package com.lichunyang.service;

import com.lichunyang.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    boolean registUser(User user) throws SQLException;

    boolean login(String username, String password) throws SQLException;

    boolean existsUsername(String username) throws SQLException;

    List<User> getUsersByLastName(String lastname) throws SQLException;

}
