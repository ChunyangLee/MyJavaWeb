package com.lichunyang.service;

import com.lichunyang.bean.User;

import java.util.List;

public interface UserService {

    boolean registUser(User user);

    boolean login(String username, String password);

    boolean existsUsername(String username);

    List<User> getUsersByLastName(String lastname);

}
