package com.lichunyang.service;

import com.lichunyang.bean.User;

public interface UserService {

    boolean registUser(User user);

    boolean login(String username, String password);

    boolean existsUsername(String username);

}
